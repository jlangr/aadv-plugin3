package plugin;

import com.intellij.openapi.project.Project;
import llms.*;
import llms.openai.OpenAI;
import llms.openai.OpenAIChatClient;
import plugin.settings.AADVSettingsState;
import ui.*;
import utils.Console;
import utils.Http;
import utils.IDGenerator;
import utils.idea.IDEAEditor;

import static java.awt.Cursor.*;
import static llms.openai.OpenAIChatClient.OPEN_AI_API_KEY;
import static llms.openai.OpenAIChatClient.OPEN_AI_MAX_TOKENS;

public class AADVController implements PromptListener, SourcePanelListener, ExampleListener {
   private static AADVController controller = null;
   private final Project project;
   AADVPromptPanel promptView = new AADVPromptPanel(this, this);
   private AADVOutputPanel outputView = new AADVOutputPanel();

   private final OpenAIChatClient openAIClient =
      new OpenAIChatClient(new Http());
   private OpenAI openAI = new OpenAI(openAIClient);
   private AADVModel model = new AADVModel();
   private IDEAEditor ide = new IDEAEditor();
   private IDGenerator idGenerator = new IDGenerator();
   private final Console console = new Console();

   Thread thread;

   private AADVController(Project project) {
      this.project = project;
   }

   public static void reset() {
      controller = null;
   }

   public static synchronized AADVController get(Project project) {
      if (controller == null)
         controller = new AADVController(project);
      return controller;
   }

   public AADVOutputPanel getOutputView() {
      return outputView;
   }

   @Override
   public void send(String text) {
      if (AADVSettingsState.instance().get(OPEN_AI_API_KEY) == null) {
         promptView.showMessage(AADVPromptPanel.MSG_KEY_NOT_CONFIGURED);
         return;
      }

      model.setPromptText(text); // TODO can this be deleted

      promptView.getParent().setCursor(getPredefinedCursor(WAIT_CURSOR));

      var prompt = new Prompt(text, model.getExampleList());

      thread = new Thread(() -> {
         try {
            var files = openAI.retrieveCompletion(prompt);
            updateSourcePanels(files);
         }
         // TODO catch and display error
         finally {
            promptView.getParent().setCursor(getDefaultCursor());
         }
      });
      thread.start();
   }

   @Override
   public void dump() {
      console.log("--- BEGIN DUMP ---");
      console.log("PROMPT:\n");
      console.log(model.dumpPrompt());
      console.log("SETTINGS:\n");
      var key = AADVSettingsState.instance().get(OPEN_AI_API_KEY);
      console.log("api key: " + (key.length() < 6 ? key : key.substring(0, 6) + "..."));
      console.log("max tokens: " + AADVSettingsState.instance().get(OPEN_AI_MAX_TOKENS));
      console.log("--- END DUMP ---");
   }

   @Override
   public void update(String text) {
      model.setPromptText(text);
   }

   // source panels

   private void updateSourcePanels(Files files) {
      files.prodFiles().forEach(this::upsertSourcePanel);
      files.testFiles().forEach(this::upsertSourcePanel);
   }

   private void upsertSourcePanel(SourceFile sourceFile) {
      var existingPanel = model.getPanel(sourceFile);
      if (existingPanel.isPresent())
         existingPanel.get().updateContent(sourceFile);
      else {
         var panel = new SourcePanel(sourceFile, this);
         model.add(panel);
         outputView.addSourcePanel(panel);
      }
   }

   // source panel listener methods:
   @Override
   public void applySourceToIDE(SourceFile sourceFile) {
      ide.replaceEditorContent(project, sourceFile);
   }

   @Override
   public void delete(SourceFile sourceFile) {
      var panel = model.getPanel(sourceFile).get();
      outputView.removeSourcePanel(panel);
      model.remove(panel);
   }

   // example listener methods

   @Override
   public void update(String id, String name, String text) {
      model.updateExample(id, name, text);
   }

   @Override
   public void delete(String id) {
      model.deleteExample(id);
      promptView.deleteExample(id);
   }

   @Override
   public void addNewExample() {
      var id = idGenerator.generate();
      model.addExample(id);
      var example = model.getExample(id);
      promptView.addNewExample(example);
   }

   @Override
   public void toggleEnabled(String id) {
      model.toggleEnabled(id);
      promptView.refreshExample(model.getExample(id));
   }

   public void setPromptView(AADVPromptPanel panel) {
      this.promptView = panel;
   }

   public void setModel(AADVModel model) {
      this.model = model;
   }

   void setIdGenerator(IDGenerator idGenerator) {
      this.idGenerator = idGenerator;
   }

   public void setIDEAEditor(IDEAEditor ideaEditor) {
      ide = ideaEditor;
   }

   public void setOutputView(AADVOutputPanel outputView) {
      this.outputView = outputView;
   }

   public void setOpenAI(OpenAI openAI) {
      this.openAI = openAI;
   }

   public Console getConsole() {
      return console;
   }
}

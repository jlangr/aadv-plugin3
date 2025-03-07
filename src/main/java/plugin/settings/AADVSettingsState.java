package plugin.settings;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@State(
   name = "plugin.settings.AADVSettingsState",
   storages = @Storage("AADVSettings.xml")
)
@Service(Service.Level.APP)
// TODO convert to Java record
public final class AADVSettingsState implements PersistentStateComponent<AADVSettingsState.State> {

   public static class State {
      public String apiKey = "";
      public List<Language> languages = new ArrayList<>();
      public String model = "gpt-4-turbo";
      public int maxTokens = 4096;
   }

   private State myState = new State();

   @Nullable
   @Override
   public State getState() {
      return myState;
   }

   @Override
   public void loadState(@NotNull State state) {
      this.myState = state;
   }

   public static AADVSettingsState getInstance() {
      return ApplicationManager.getApplication().getService(AADVSettingsState.class);
   }

   public int getMaxTokens() {
      return myState.maxTokens;
   }

   public void setMaxTokens(int maxTokens) {
      myState.maxTokens = maxTokens;
   }

   public String getModel() {
      return myState.model;
   }

   public void setModel(String model) {
      myState.model = model;
   }

   public String getApiKey() {
      return myState.apiKey;
   }

   public void setApiKey(String apiKey) {
      myState.apiKey = apiKey;
   }

   public StyleSettings getStyleSettings() {
      return new StyleSettings(myState.languages).clone(); // Return a deep copy
   }

   public void setStyleSettings(StyleSettings styleSettings) {
      myState.languages = styleSettings.languages();
   }

   public LLMAPISettings getLLMAPISettings() {
      return new LLMAPISettings(myState.apiKey);
   }

   public void setLLMAPISettings(LLMAPISettings llmapiSettings) {
      myState.apiKey = llmapiSettings.apiKey();
   }
}

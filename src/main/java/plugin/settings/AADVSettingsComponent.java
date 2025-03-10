package plugin.settings;

import com.intellij.ui.components.JBTabbedPane;

import javax.swing.*;
import java.awt.*;

public class AADVSettingsComponent extends JPanel {
   public static final String MSG_LLM_APIS = "LLM APIs";
   public static final String MSG_CODING_STYLE = "Coding Style";

   private final LLMAPISettingsComponent llmapiSettingsComponent;

   public AADVSettingsComponent(LLMAPISettingsComponent llmapiSettingsComponent) {
      this.llmapiSettingsComponent = llmapiSettingsComponent;

      setLayout(new BorderLayout());
      var tabbedPane = new JBTabbedPane();
      tabbedPane.addTab(MSG_LLM_APIS, llmapiSettingsComponent.getPanel());
      add(tabbedPane, BorderLayout.CENTER);
   }

   public boolean isModified() {
      return llmapiSettingsComponent.isModified();
   }

   public void apply() {
      // do we need these conditionals
      if (llmapiSettingsComponent.isModified())
         llmapiSettingsComponent.apply();
   }

   public void reset() {
      llmapiSettingsComponent.reset();
   }
}

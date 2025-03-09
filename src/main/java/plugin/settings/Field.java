package plugin.settings;

import com.intellij.ui.components.JBTextField;

public record Field(Setting setting, JBTextField textField) {
   public String getText() {
      return textField().getText();
   }

   String settingsValue() {
      return setting().retriever().get();
   }
}

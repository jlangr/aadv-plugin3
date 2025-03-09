package plugin.settings;

import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.JBUI;
import llms.openai.OpenAISettings;
import utils.Reflect;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SettingsComponent {
   private final JPanel panel;
   private final List<Field> fields;
   private final List<String> settings;

   public SettingsComponent(List<String> settings) {
      this.settings = settings;
      panel = new JPanel(new GridBagLayout());
      fields = loadFields();
      layoutFields();
   }

   protected void layoutFields() {
      var gbc = new GridBagConstraints();
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.insets = JBUI.insets(5);

      for (var i = 0; i < fields.size(); i++) {
         var field = fields.get(i);
         gbc.gridx = 0;
         gbc.gridy = i;
         gbc.weightx = 0;
         panel.add(new JLabel(field.setting().label() + ":"), gbc);

         gbc.gridx = 1;
         gbc.gridy = i;
         gbc.weightx = 1.0;
         panel.add(field.textField(), gbc);
      }
   }

   protected List<Field> loadFields() {
      return settings.stream().map(name -> {
         var setting = OpenAISettings.allSettings.get(name);

         var textField = new JBTextField(setting.retriever().get());
         var inputVerifier = Reflect.instantiate(setting.inputVerifierClass());
         textField.setInputVerifier(inputVerifier);

         return new Field(setting, textField);
      }).toList();
   }

   public JPanel getPanel() {
      return panel;
   }

   public boolean isModified() {
      return fields.stream().anyMatch(field -> !field.getText().equals(field.settingsValue()));
   }

   public void apply() {
      fields.forEach(field -> field.setting().setter().accept(field.getText()));
   }

   public void reset() {
      fields.forEach(field -> field.textField().setText(field.settingsValue()));
   }
}

package plugin.settings;

import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import java.awt.*;

public class LLMAPISettingsComponent {
   private final JPanel panel;
   private final JBTextField apiKeyField;
   private final JBTextField maxTokensField;

   class NumericInputVerifier extends InputVerifier {
      @Override
      public boolean verify(JComponent input) {
         var text = ((JBTextField) input).getText();
         try {
            var value = Integer.parseInt(text);
            return value > 0;
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(panel, "Please enter a valid number for Max Tokens.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return false;
         }
      }
   }

   public LLMAPISettingsComponent(LLMAPISettings llmapiSettings) {
      panel = new JPanel(new GridBagLayout());
      apiKeyField = new JBTextField(llmapiSettings.apiKey());
      maxTokensField = new JBTextField(String.valueOf(llmapiSettings.maxTokens()));
      maxTokensField.setInputVerifier(new NumericInputVerifier());

      var gbc = new GridBagConstraints();
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.insets = JBUI.insets(5);

      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.weightx = 0;
      panel.add(new JLabel("OpenAI API Key:"), gbc);

      gbc.gridx = 1;
      gbc.gridy = 0;
      gbc.weightx = 1.0;
      panel.add(apiKeyField, gbc);

      gbc.gridx = 0;
      gbc.gridy = 1;
      gbc.weightx = 0;
      panel.add(new JLabel("Max Tokens:"), gbc);

      gbc.gridx = 1;
      gbc.gridy = 1;
      gbc.weightx = 1.0;
      panel.add(maxTokensField, gbc);
   }

   public JPanel getPanel() {
      return panel;
   }

   public boolean isModified() {
      var savedApiKey = settings().getApiKey();
      var savedMaxTokens = settings().getMaxTokens();
      return !apiKeyField.getText().equals(savedApiKey) || !maxTokensField.getText().equals(String.valueOf(savedMaxTokens));
   }

   public void apply() {
      settings().setApiKey(apiKeyField.getText());
      settings().setMaxTokens(Integer.parseInt(maxTokensField.getText()));
   }

   private AADVSettingsState settings() {
      return AADVSettingsState.get();
   }

   public void reset() {
      var savedApiKey = settings().getApiKey();
      var savedMaxTokens = settings().getMaxTokens();
      apiKeyField.setText(savedApiKey);
      maxTokensField.setText(String.valueOf(savedMaxTokens));
   }
}
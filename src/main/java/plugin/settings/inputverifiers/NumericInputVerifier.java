package plugin.settings.inputverifiers;

import com.intellij.ui.components.JBTextField;

import javax.swing.*;

public class NumericInputVerifier extends InputVerifier {
   private final JComponent parent;

   // TODO: construct with the component parent
   public NumericInputVerifier(JComponent parent) {
      this.parent = parent;
   }

   @Override
   public boolean verify(JComponent input) {
      try {
         var text = ((JBTextField) input).getText();
         return Integer.parseInt(text) > 0;
      } catch (NumberFormatException e) {
         JOptionPane.showMessageDialog(parent, "Please enter a valid number for Max Tokens.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
         return false;
      }
   }
}

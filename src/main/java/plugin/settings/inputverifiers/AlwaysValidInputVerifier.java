package plugin.settings.inputverifiers;

import javax.swing.*;

public class AlwaysValidInputVerifier extends InputVerifier {
   public AlwaysValidInputVerifier(JComponent parent) {}

   @Override
   public boolean verify(JComponent input) {
      return true;
   }
}

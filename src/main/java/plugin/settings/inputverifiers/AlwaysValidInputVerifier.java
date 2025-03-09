package plugin.settings.inputverifiers;

import javax.swing.*;

public class AlwaysValidInputVerifier extends InputVerifier {
   @Override
   public boolean verify(JComponent input) {
      return true;
   }
}

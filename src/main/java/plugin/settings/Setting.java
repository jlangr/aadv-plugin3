package plugin.settings;

import plugin.settings.inputverifiers.AlwaysValidInputVerifier;

import javax.swing.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public record Setting(String key, String label, Supplier<String> retriever, Consumer<String> setter, Class<? extends InputVerifier> inputVerifierClass) {
   public Setting(String key, String label, Supplier<String> retriever, Consumer<String> setter) {
      this(key, label, retriever, setter, AlwaysValidInputVerifier.class);
   }
}

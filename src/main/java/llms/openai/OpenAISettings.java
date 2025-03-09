package llms.openai;

import plugin.settings.AADVSettingsState;
import plugin.settings.Setting;
import plugin.settings.inputverifiers.NumericInputVerifier;

import java.util.Map;

public class OpenAISettings {
   public static final Map<String, Setting> allSettings = Map.of(
      "openai-api-key",
      new Setting("openai-api-key",
         "OpenAI API Key",
         () -> AADVSettingsState.get().getApiKey(),
         (String fieldValue) -> AADVSettingsState.get().setApiKey(fieldValue)
      ),

      "max-tokens",
      new Setting("max-tokens",
         "Max Tokens",
         () -> String.valueOf(AADVSettingsState.get().getMaxTokens()),
         (String fieldValue) -> AADVSettingsState.get().setMaxTokens(Integer.parseInt(fieldValue)),
         NumericInputVerifier.class
      )
   );
}

package llms.openai;

import plugin.settings.AADVSettingsState;
import plugin.settings.Setting;
import plugin.settings.inputverifiers.NumericInputVerifier;

import java.util.Map;

import static llms.openai.OpenAIChatClient.OPEN_AI_API_KEY;
import static llms.openai.OpenAIChatClient.OPEN_AI_MAX_TOKENS;

public class OpenAISettings {
   public static final Map<String, Setting> allSettings = Map.of(
      OPEN_AI_API_KEY,
      new Setting(OPEN_AI_API_KEY,
         "OpenAI API Key",
         "",
         () -> AADVSettingsState.get().get(OPEN_AI_API_KEY),
         (String fieldValue) -> AADVSettingsState.get().set(OPEN_AI_API_KEY, fieldValue)
      ),

      OPEN_AI_MAX_TOKENS,
      new Setting(OPEN_AI_MAX_TOKENS,
         "Max Tokens",
         "4096",
         () -> String.valueOf(AADVSettingsState.get().get(OPEN_AI_MAX_TOKENS)),
         (String fieldValue) -> AADVSettingsState.get().set(OPEN_AI_MAX_TOKENS, fieldValue),
         NumericInputVerifier.class
      )
   );
}

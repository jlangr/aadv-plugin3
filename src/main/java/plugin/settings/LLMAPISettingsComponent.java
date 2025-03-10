package plugin.settings;

import java.util.List;

import static llms.openai.OpenAIChatClient.OPEN_AI_API_KEY;
import static llms.openai.OpenAIChatClient.OPEN_AI_MAX_TOKENS;

public class LLMAPISettingsComponent extends SettingsComponent {
   public LLMAPISettingsComponent() {
      super(List.of(OPEN_AI_API_KEY, OPEN_AI_MAX_TOKENS));
   }

}
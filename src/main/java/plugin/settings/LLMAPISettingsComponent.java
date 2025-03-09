package plugin.settings;

import java.util.List;

public class LLMAPISettingsComponent extends SettingsComponent {
   public LLMAPISettingsComponent() {
      super(List.of("openai-api-key", "max-tokens"));
   }

}
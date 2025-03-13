package plugin.settings;

import java.util.HashMap;
import java.util.Map;

import static llms.openai.OpenAIChatClient.OPEN_AI_API_KEY;
import static llms.openai.OpenAIChatClient.OPEN_AI_MAX_TOKENS;

public class State {
   private static final Map<String, String> values = new HashMap<>();

   State() {
      // TODO pull from elsewhere
      values.put("model", "gpt-4-turbo");
      values.put(OPEN_AI_API_KEY, "");
      values.put(OPEN_AI_MAX_TOKENS, "4096");
   }

   public void set(String key, String value) {
      values.put(key, value);
   }

   public String get(String key) {
      return values.get(key);
   }
}

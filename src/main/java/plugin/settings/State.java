package plugin.settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static llms.openai.OpenAIChatClient.OPEN_AI_API_KEY;
import static llms.openai.OpenAIChatClient.OPEN_AI_MAX_TOKENS;

public class State {
//   public String apiKey = "";
//   public List<Language> languages = new ArrayList<>();
//   public String model = "gpt-4-turbo";
   public Map<String, Object> values = new HashMap<>();

   State() {
      values.put(OPEN_AI_API_KEY, "");
      values.put("model", "gpt-4-turbo");
      values.put(OPEN_AI_MAX_TOKENS, "4096");
      values.put("languages", new ArrayList<>());
   }

   public void set(String key, Object value) {
      values.put(key, value);
   }

   public String get(String key) {
      return (String) values.get(key);
   }

   public int getInt(String key) {
      return Integer.parseInt(get(key));
   }
}

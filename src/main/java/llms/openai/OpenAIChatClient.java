package llms.openai;

import plugin.settings.AADVSettingsState;
import utils.Http;

import java.util.Map;

public class OpenAIChatClient {
   public static final String API_URL = "https://api.openai.com/v1/chat/completions";
   public static final String OPEN_AI_API_KEY = "open-ai-api-key";
   public static final String OPEN_AI_MAX_TOKENS = "max-tokens";

   private final Http http;

   public OpenAIChatClient(Http http) {
      this.http = http;
   }

   public ChatCompletionResponse retrieveCompletion(Map<Object, Object> requestBody) {
      var apiKey = AADVSettingsState.get().get(OPEN_AI_API_KEY);

      var request = http.createPostRequest(requestBody, apiKey, API_URL);

      return (ChatCompletionResponse)http.send(request);
   }
}

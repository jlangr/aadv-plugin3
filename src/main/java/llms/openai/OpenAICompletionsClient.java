package llms.openai;

import plugin.settings.AADVPluginSettings;
import utils.Http;
import java.util.Map;

public class OpenAICompletionsClient {
   public static final String API_URL = "https://api.openai.com/v1/completions";

   private final Http http;
   private final AADVPluginSettings aadvPluginSettings;

   public OpenAICompletionsClient(Http http, AADVPluginSettings aadvPluginSettings) {
      this.http = http;
      this.aadvPluginSettings = aadvPluginSettings;
   }

   public ChatCompletionResponse retrieveCompletion(Map<Object, Object> requestBody) {
      var apiKey = aadvPluginSettings.retrieveAPIKey();

      var request = http.createPostRequest(requestBody, apiKey, API_URL);

      return (ChatCompletionResponse)http.send(request);
   }
}

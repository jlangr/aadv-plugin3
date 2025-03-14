package llms.openai;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import plugin.settings.AADVSettingsState;
import utils.Http;
import java.net.http.HttpRequest;
import java.util.HashMap;
import static llms.openai.OpenAIChatClient.API_URL;
import static llms.openai.OpenAIChatClient.OPEN_AI_API_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnOpenAIClient {
   @Mock
   HttpRequest request;
   HashMap<Object, Object> requestBody = new HashMap<>();

   @Mock
   Http http;

   @InjectMocks
   OpenAIChatClient client;

   @Test
   void retrieveCompletion() {
      var settingsState = new AADVSettingsState();
      settingsState.set(OPEN_AI_API_KEY, "apikey");
      AADVSettingsState.set(settingsState);
      var chatCompletionResponse = new ChatCompletionResponseBuilder().build();
//      when(aadvPluginSettings.retrieveAPIKey()).thenReturn("apikey");
      when(http.createPostRequest(eq(requestBody), eq("apikey"), eq(API_URL)))
         .thenReturn(request);
      when(http.send(request)).thenReturn(chatCompletionResponse);

      var actualResponse = client.retrieveCompletion(requestBody);

      assertEquals(chatCompletionResponse, actualResponse);
   }
}
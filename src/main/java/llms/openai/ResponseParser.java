package llms.openai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseParser {
   public ChatCompletionResponse parse(String jsonResponse) throws JsonProcessingException {
      System.out.println("JSON: " + jsonResponse);
      return new ObjectMapper().readValue(jsonResponse, ChatCompletionResponse.class);
   }
}

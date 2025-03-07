package llms.openai;

import llms.*;
import plugin.settings.AADVSettingsState;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class OpenAI {
   static final String MESSAGE_ROLE_USER = "user";
   public static final String MESSAGE_TYPE_SYSTEM = "system";

   private final OpenAIChatClient client;

   public OpenAI(OpenAIChatClient client) {
      this.client = client;
   }

   public Files retrieveCompletion(Prompt prompt) {
      var requestBody = createRequestBody(toOpenAIMessages(prompt));
      var completion = client.retrieveCompletion(requestBody);
      return new CodeResponseSplitter().split(completion.firstMessageContent());
   }

   // still needed?
   private Message[] toOpenAIMessages(Prompt prompt) {
      return prompt.messages().stream()
         .map(promptMessage ->
            new Message(role(promptMessage.promptMessageType()), promptMessage.text()))
         .toArray(Message[]::new);
   }

   private String role(PromptMessageType promptMessageType) {
      return switch(promptMessageType) {
         case SYSTEM -> MESSAGE_TYPE_SYSTEM;
         case USER -> MESSAGE_ROLE_USER;
      };
   }

   private HashMap<Object, Object> createRequestBody(Message[] messages) {
      var requestBody = new HashMap<>();
      requestBody.put("model", AADVSettingsState.getInstance().getModel());
      requestBody.put("messages", messages);
      requestBody.put("max_tokens", AADVSettingsState.getInstance().getMaxTokens());
      requestBody.put("temperature", 0);
      return requestBody;
   }
}

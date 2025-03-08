package llms.openai;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Message(String role, String content, String refusal) {
   public Message(String role, String content) {
      this(role, content, null);
   }
}

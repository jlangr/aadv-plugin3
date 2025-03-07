package llms.openai;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Choice(
   int index,
   String text,
   Object logprobs,
   String finishReason
) {}

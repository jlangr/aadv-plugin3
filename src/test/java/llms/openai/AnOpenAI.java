package llms.openai;

import llms.ExampleList;
import llms.Prompt;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import plugin.settings.AADVSettingsState;

import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnOpenAI {
   @InjectMocks
   OpenAI openAI;

   @Mock
   OpenAIChatClient client;

   @BeforeEach
   void setUp() {
      AADVSettingsState.set(new AADVSettingsState());
   }

   @AfterEach
   void cleanUp() {
      AADVSettingsState.reset();
   }

   @Test
   void retrieveCompletionExtractsSourceFilesFromResponse() {
      var prompt = new Prompt("text", new ExampleList());
      var response = new ChatCompletionResponseBuilder().addChoice("""
         /* prod class FizzBuzz.java */
         class FizzBuzz {}
         /* end prod class */
         """).build();
      when(client.retrieveCompletion(any(HashMap.class))).thenReturn(response);

      var files = openAI.retrieveCompletion(prompt);

      var prodSource = files.prodFiles().get(0).source();
      assertEquals("class FizzBuzz {}", prodSource);
   }
}
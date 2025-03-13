package plugin.settings;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static llms.openai.OpenAIChatClient.OPEN_AI_MAX_TOKENS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AnAADVSettingsState {
   @Mock
   Application application;
   AADVSettingsState settingsState;

   @BeforeEach
   void setup() {
      ApplicationManager.setApplication(application);
      Mockito.lenient().when(application.getService(AADVSettingsState.class))
         .thenReturn(new AADVSettingsState());
      settingsState = AADVSettingsState.instance();
   }

   @Test
   void returnsDefaultValue() {
      assertEquals("4096", settingsState.get(OPEN_AI_MAX_TOKENS));
   }

   @Test
   void getReturnsSetValue() {
      settingsState.set("x", "123");

      assertEquals("123", settingsState.get("x"));
   }
}
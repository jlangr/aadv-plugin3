package plugin.settings;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static llms.openai.OpenAIChatClient.OPEN_AI_MAX_TOKENS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AnAADVSettingsState {
   Application application;

   // TODO left off here
   @Test
   void x() {
      application = mock(Application.class);
      ApplicationManager.setApplication(application);
      var settingsState = new AADVSettingsState();
      when(application.getService(AADVSettingsState.class))
         .thenReturn(settingsState);

      assertEquals("4096", settingsState.get(OPEN_AI_MAX_TOKENS));
   }

   @Test
   void getReturnsStoredSettingsState() {
//      storeOnApplication(new AADVSettingsStateBuilder()
//         .withApiKey("123")
//         .withLanguage("Java", "Rule 1", "Rule 2")
//         .withMaxTokens(42)
//         .build());
//
//      var settingsState = AADVSettingsState.get();
//
//      assertEquals("123", settingsState.getApiKey());
//      var java = settingsState.getStyleSettings().languages().get(0);
//      assertEquals("Java", java.getName());
//      assertEquals("Rule 1", java.getRules().get(0).getText());
//      assertEquals("Rule 2", java.getRules().get(1).getText());
//      assertEquals(42, settingsState.getMaxTokens());
   }

   private void storeOnApplication(AADVSettingsState storedSettingsState) {
      when(application.getService(AADVSettingsState.class)).thenReturn(storedSettingsState);
   }

   @Test
   void loadsFromSettingsState() {
//      AADVSettingsState.reset();
//      var settingsState = new AADVSettingsState();
//      storeOnApplication(settingsState);
////      state.languages = List.of(new Language("Ruby", List.of(new Rule(""))));
//
//      AADVSettingsState.get().loadState(new State());
//
//      assertEquals("123", AADVSettingsState.get().getApiKey());
   }

}
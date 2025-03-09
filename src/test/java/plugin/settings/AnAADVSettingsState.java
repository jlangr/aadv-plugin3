package plugin.settings;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AnAADVSettingsState {
   Application application;

   @BeforeEach
   void setApplication() {
      application = mock(Application.class);
      ApplicationManager.setApplication(application);
      AADVSettingsState.reset();
   }

   @Test
   void getReturnsStoredSettingsState() {
      storeOnApplication(new AADVSettingsStateBuilder()
         .withApiKey("123")
         .withLanguage("Java", "Rule 1", "Rule 2")
         .withMaxTokens(42)
         .build());

      var settingsState = AADVSettingsState.get();

      assertEquals("123", settingsState.getApiKey());
      var java = settingsState.getStyleSettings().languages().get(0);
      assertEquals("Java", java.getName());
      assertEquals("Rule 1", java.getRules().get(0).getText());
      assertEquals("Rule 2", java.getRules().get(1).getText());
      assertEquals(42, settingsState.getMaxTokens());
   }

   private void storeOnApplication(AADVSettingsState storedSettingsState) {
      when(application.getService(AADVSettingsState.class)).thenReturn(storedSettingsState);
   }

   @Test
   void loadsFromSettingsState() {
      storeOnApplication(new AADVSettingsStateBuilder().withApiKey("999").build());
      var state = new AADVSettingsState.State();
      state.apiKey = "123";
      state.languages = List.of(new Language("Ruby", List.of(new Rule(""))));

      AADVSettingsState.get().loadState(state);

      assertEquals("123", AADVSettingsState.get().getApiKey());
   }

}
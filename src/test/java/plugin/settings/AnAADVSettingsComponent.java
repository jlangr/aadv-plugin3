package plugin.settings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static plugin.settings.AADVSettingsComponent.MSG_CODING_STYLE;
import static plugin.settings.AADVSettingsComponent.MSG_LLM_APIS;

class AnAADVSettingsComponent {
   private AADVSettingsComponent aadvSettingsComponent;
   private LLMAPISettingsComponent mockLLMAPISettingsComponent;

   @BeforeEach
   void setUp() {
      AADVSettingsState.set(new AADVSettingsState());
      mockLLMAPISettingsComponent = mock(LLMAPISettingsComponent.class);
      when(mockLLMAPISettingsComponent.getPanel()).thenReturn(new JPanel());
      aadvSettingsComponent = new AADVSettingsComponent(mockLLMAPISettingsComponent);
   }

   @Test
   void isModified() {
      when(mockLLMAPISettingsComponent.isModified()).thenReturn(true);
      assertTrue(aadvSettingsComponent.isModified());

      when(mockLLMAPISettingsComponent.isModified()).thenReturn(false);
      assertFalse(aadvSettingsComponent.isModified());
   }

   @Test
   void apply() {
      when(mockLLMAPISettingsComponent.isModified()).thenReturn(true);
      aadvSettingsComponent.apply();
      verify(mockLLMAPISettingsComponent, times(1)).apply();
   }

   @Test
   void reset() {
      aadvSettingsComponent.reset();
      verify(mockLLMAPISettingsComponent, times(1)).reset();
   }

   @Test
   void overallLayoutEmploysTabbedPane() {
      var components = aadvSettingsComponent.getComponents();
      assertEquals(1, components.length);

      var tabbedPane = (JTabbedPane) components[0];
      assertEquals(1, tabbedPane.getTabCount());
   }

   @Test
   void apiSettings() {
      assertEquals(MSG_LLM_APIS, getTabbedPane().getTitleAt(0));
   }

   private JTabbedPane getTabbedPane() {
      return (JTabbedPane) aadvSettingsComponent.getComponents()[0];
   }
}

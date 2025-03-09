package plugin.settings;

// TODO This all seems overblown--this could go away?
// What's the possibility of the settingsState instance being null

public class AADVPluginSettings {
   public String xretrieveAPIKey() {
      var settingsState = AADVSettingsState.get();
      return settingsState.getApiKey();
   }

   public int retrieveMaxTokens() {
      var settingsState = AADVSettingsState.get();
      return settingsState.getMaxTokens();
   }
}

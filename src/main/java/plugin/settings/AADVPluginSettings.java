package plugin.settings;

// TODO This all seems overblown--this could go away?
// What's the possibility of the settingsState instance being null

public class AADVPluginSettings {
   public String retrieveAPIKey() {
      var settingsState = AADVSettingsState.getInstance();
      if (settingsState == null || settingsState.getApiKey() == null)
         return null;

      return settingsState.getApiKey().trim();
   }

   public int retrieveMaxTokens() {
      var settingsState = AADVSettingsState.getInstance();
      return settingsState.getMaxTokens();
   }
}

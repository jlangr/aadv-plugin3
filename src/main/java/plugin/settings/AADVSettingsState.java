package plugin.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
   name = "plugin.settings.AADVSettingsState",
   storages = @Storage("AADVSettings.xml")
)
@Service(Service.Level.APP)
// TODO convert to Java record. How else can this be streamlined?
public final class AADVSettingsState implements PersistentStateComponent<plugin.settings.State> {
   private plugin.settings.State myState = new plugin.settings.State();

   private static AADVSettingsState instance;

   public static AADVSettingsState instance() {
      if (instance == null)
         instance = ApplicationManager.getApplication().getService(AADVSettingsState.class);
      return instance;
   }

   public static void reset() {
      instance = null;
   }

   public static void set(AADVSettingsState settingsState) {
      instance = settingsState;
   }

   @Nullable
   @Override
   public plugin.settings.State getState() {
      return myState;
   }

   @Override
   public void loadState(@NotNull plugin.settings.State state) {
      this.myState = state;
   }

   public String get(String key) {
      return myState.get(key);
   }

   public void set(String key, String value) {
      myState.set(key, value);
   }
}

package utils;

import org.jetbrains.annotations.NotNull;
import java.lang.reflect.InvocationTargetException;

public class ReflectUtils {
   private ReflectUtils() {
   }

   public static <T> T instantiate(@NotNull Class<T> aClass, @NotNull Object parameter) {
      try {
         return aClass.getDeclaredConstructor(parameter.getClass()).newInstance(parameter);
      } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
         throw new RuntimeException("Unable to construct object of class " + aClass.getName(), e);
      }
   }
}

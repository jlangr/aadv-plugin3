package utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectUtils {
   private ReflectUtils() {
   }

   public static <T> T instantiate(Class<T> aClass, Object parameter) {
      try {
         var constructor = findMatchingConstructor(aClass, parameter.getClass());
         if (constructor == null) {
            throw new RuntimeException("No suitable constructor found for " + aClass + " and parameter type " + parameter.getClass());
         }
         return constructor.newInstance(parameter);
      } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
         throw new RuntimeException("Unable to construct object of class " + aClass.getName(), e);
      }
   }

   static <T, P> Constructor<T> findMatchingConstructor(Class<T> aClass, Class<? extends P> paramClass) {
      return java.util.Arrays.stream(aClass.getDeclaredConstructors())
         .filter(c ->
            c.getParameterTypes().length == 1 && c.getParameterTypes()[0].isAssignableFrom(paramClass))
         .map(c -> (Constructor<T>)c)  // unchecked cast; should be safe due to filtering
         .findFirst()
         .orElse(null);
   }
}

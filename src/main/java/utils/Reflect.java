package utils;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

// TODO test
public class Reflect {
   private Reflect() {}

   public static @NotNull InputVerifier instantiate(Class<? extends InputVerifier> aClass) {
      try {
         return aClass.getDeclaredConstructor().newInstance();
      } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
         throw new RuntimeException("Unable to construct input verifier", e);
      }
   }
}

package utils;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class SomeReflectUtils {
   @Nested
   class Instantiate {
      @Test
      void returnsNewObjectOfSpecifiedType() {
         var result = ReflectUtils.instantiate(File.class, "ABC");

         assertEquals("ABC", result.getName());
      }

      static class BaseClass {}
      static class Subclass extends BaseClass {}

      static class ToInstantiate {
         boolean wasInstantiated;
         ToInstantiate(BaseClass b) { wasInstantiated = true; }
      }

      @Test
      void returnsNewObjectWhenParameterTypeIsSubclassOfConstructorParameterType() {
         var result = ReflectUtils.instantiate(ToInstantiate.class, new Subclass());

         assertTrue(result.wasInstantiated);
      }
   }
}
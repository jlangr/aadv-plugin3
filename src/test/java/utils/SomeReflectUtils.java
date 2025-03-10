package utils;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

class SomeReflectUtils {
   @Nested
   class Instantiate {
      @Test
      void ReturnsNewObjectOfSpecifiedType() {
         File result = ReflectUtils.instantiate(File.class, "ABC");

         assertThat(result.getName(), equalTo("ABC"));
      }
   }
}
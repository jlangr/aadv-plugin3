package llms;

import java.util.List;
import static java.lang.String.format;
import static llms.PromptMessageType.SYSTEM;
import static llms.PromptMessageType.USER;

public record Prompt(String promptText, ExampleList examples) {
   static final String LINE = "%n%s%n";
   public static final String ASSISTANT_GUIDELINES = """
      You're a Java programming assistant. When asked to generate solution code,
      include only code. Don't include any explanation. Don't include comments in any code.""";
   static final String CODE_STYLE = """
      - When possible, prefer functional solutions, with functional methods and immutable classes. Avoid side effects.
      - Extract implementation specifics to separate cohesive methods.
      - Extract conditionals to separate predicate methods.
      - Minimize use of temporary variables. Make calls to methods instead.
      """;
   static final String LANGUAGE_SPECIFIC_CODE_STYLE = """
      - Within chained calls using the streams interface, extract lambda bodies with implementation details to separate methods.
      - Create instance-side methods by default. Do not use static methods unless appropriate or otherwise asked.
      - In tests, do not start the name of the test method with the word "test".
      """;

   public List<PromptMessage> messages() {
      return List.of(
         new PromptMessage(SYSTEM, ASSISTANT_GUIDELINES),
         new PromptMessage(SYSTEM, CODE_STYLE),
         new PromptMessage(SYSTEM, LANGUAGE_SPECIFIC_CODE_STYLE),
         new PromptMessage(USER, prompt()));
   }

   static final String PROMPT_OVERVIEW = """
      Generate JUnit test class(es) and production Java code for the solution.
      In output, begin each code listing with a header in either the form:
      /* test class TestFileName.java */
      or:
      /* prod class ProdFileName.java */
      End each code listing with a footer, either:
      /* end test class */
      or:
      /* end prod class */.
      Substitute the real file name for TestFileName and ProdFileName.""";
   static final String PROMPT_HEADER = "Generate code for this:";
   static final String EXAMPLES_HEADER = "Examples:";

   private String prompt() {
      return format(LINE, PROMPT_OVERVIEW) +
         format(LINE, PROMPT_HEADER) +
         promptText +
         format(LINE, EXAMPLES_HEADER) +
         format(LINE, examples.toPromptText());
   }
}

package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import llms.openai.ResponseParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class Http {
   public Object send(HttpRequest request) {
      var client = HttpClient.newHttpClient();
      var response = httpSend(client, request);
      if (response.statusCode() == 200) {
         try {
            return new ResponseParser().parse(response.body());
         } catch (Exception e) {
            throw new JsonException("Error parsing response ", e);
         }
      } else {
         throw new HttpException("Error retrieving response " + response.statusCode() + "\n" + response.body());
      }
   }

   public HttpRequest createPostRequest(Map<Object, Object> requestBody, String apiKey, String apiUrl) {
      var jsonRequestBody = toJson(requestBody);
      System.out.println("SENDING: "+ jsonRequestBody);

      return HttpRequest.newBuilder()
         .uri(URI.create(apiUrl))
         .header("Content-Type", "application/json")
         .header("Authorization", "Bearer " + apiKey)
         .POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody))
         .build();
   }

   private HttpResponse<String> httpSend(HttpClient client, HttpRequest request) {
      try {
         return client.send(request, HttpResponse.BodyHandlers.ofString());
      } catch (Exception e) {
         // TODO change to logger
         System.out.println(e.fillInStackTrace());
         throw new HttpException(e);
      }
   }

   private String toJson(Map<Object, Object> requestBody) {
      try {
         return new ObjectMapper().writeValueAsString(requestBody);
      } catch (JsonProcessingException e) {
         throw new JsonException("unable to create JSON from " + requestBody, e);
      }
   }
}

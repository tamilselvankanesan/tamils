package com.success.programs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.success.programs.dto.Country;
import com.success.programs.dto.State;

public class GsonMapperTest {

  public static void main(String[] args) {
    // List<Class<Country>> countries = fromJson(Country.class, restGet("http://localhost:8080/ndb/rest/countries"));
    List<Country> countries = fromJson(restGet("http://localhost:8080/ndb/rest/countries"), Country.class);
    for (Country c : countries) {
      System.out.print(" c name = " + c.getName() +" date "+c.getCreatedDate());  
    }
    List<State> states = fromJson(restGet("http://localhost:8080/ndb/rest/state/TN"), State.class);
    for (State s : states) {
      System.out.print("s name = " + s.getName());
    }
    parseAsJsonObject(restGet("http://localhost:8080/ndb/rest/state/country/IN"));
    toJson();
    parseAsJsonObject1(restGet("http://localhost:8080/ndb/hello/method1"));
  }

  static <T> List<T> fromJson(String data, Class<T> target) {
    JsonElement e = new JsonParser().parse(data);
    List<T> result = new ArrayList<>();
    Gson gson = new GsonBuilder().registerTypeAdapter(java.util.Date.class, new JsonDeserializer<java.util.Date>() {
      @Override
      public java.util.Date deserialize(JsonElement je, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(je.getAsJsonPrimitive().getAsLong());
        return c.getTime(); 
      }}).create();
    if(e instanceof JsonArray){
      JsonArray arr = new JsonParser().parse(data).getAsJsonArray();
      for (JsonElement el : arr) {
        result.add(gson.fromJson(el, target));
      }
    }else{
      result.add(gson.fromJson(e, target));
    }
    return result;  
  }
  static void parseAsJsonObject(String data) {
    JsonArray arr = new JsonParser().parse(data).getAsJsonArray();
    for(JsonElement jb : arr){
      System.out.println("parse as json object: "+jb.getAsJsonObject().get("name"));
    }
  }
  static void parseAsJsonObject1(String data) {
    JsonElement je = new JsonParser().parse(data);
    System.out.println(je.getAsJsonObject().get("name"));
    int count = je.getAsJsonObject().getAsJsonPrimitive("count").getAsInt();
    System.out.println("Count is "+count);
//    System.out.println("parse as json object: "+jb.getAsJsonObject().get("name"));
  }

  static void toJson(){
    Country c = new Country();
    State s = new State();
    s.setCode("AP");
    s.setName("Andhra Pradhesh");
//    List<State> list = new ArrayList<>();
//    list.add(s);
//    c.setStates(list);
    c.setCode("IN");
    s.setCountry(c);
    Gson g = new Gson();
    sendPost("http://localhost:8080/ndb/rest/state/add", g.toJson(s));
  }
  
  static void sendPost(String urlStr, String data){
    System.out.println("data to post "+data);
    try {
      URL url = new URL(urlStr);
      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/json");
      conn.setDoOutput(true);
      OutputStream os = conn.getOutputStream();
      os.write(data.getBytes());
      os.flush();
      
      if(conn.getResponseCode()==200){
        System.out.println("post successful");
      }else{
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        while ((line = reader.readLine()) != null) {
          System.out.println(line);
        }
        System.out.println("post failed");
      }
    }
    catch (MalformedURLException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    
  }
  
  static String restGet(String urlString) {
    StringBuilder data = new StringBuilder();
    try {
      URL url = new URL(urlString);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      String line;

      if (conn.getResponseCode() == 200) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        while ((line = reader.readLine()) != null) {
          data.append(line);
        }
      }
      else {
        System.out.println(" failed ..");
      }
      System.out.println(" data ->" + data.toString());
    }
    catch (MalformedURLException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return data.toString();
  }
  static void usingTypeToken(String data){
    Type sourceObjectType = new TypeToken<List<Country>>() {}.getType();
    Gson gson = new Gson();
    List<Country> countries =   gson.fromJson(data, sourceObjectType);
   }
  static void usingCustomSerializer(State data){
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(State.class, new GsonCustomDeserializer());
    String json = builder.create().toJson(data);
  }
  static void usingCustomDeSerializer(String data){
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(State.class, new GsonCustomDeserializer());
    State s = builder.create().fromJson(data, State.class);
  }
}

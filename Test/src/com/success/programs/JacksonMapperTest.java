package com.success.programs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.success.programs.dto.Country;
import com.success.programs.dto.State;

public class JacksonMapperTest {

  public static void main(String[] args) {
    try {
    	searlizePerson();
      serializeJRP();
      deseralize();
      System.out.println("Before calling rest");
      deseralize(callRest());
      System.out.println("Before calling rest post");
      callRestPost();
      System.out.println("Before calling rest state");
      deseralizeState(callRestState());
      System.out.println("Before doing json node");
      parseUsingJsonNode(callRest());
      System.out.println("Before doing state json node");
      parseStateUsingJsonNode(callRestState());
      System.out.println("custom serializer");
      writeUsingCustomSerializer();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  static void searlizePerson() throws JsonProcessingException {
	  Result r = new Result();
	  Person p = new Person();
	  p.setFirstName("Amy");
	  p.setLastName("Jackson");
	  p.setPersonId("AAA-BBBB-CCCCC");
	  Education e = new Education();
	  e.setDegree("B.Tech");
	  e.setFieldOfStudy("IT");
	  e.setFromYear((short)2005);
	  List<Education> eList = new java.util.ArrayList<Education>();
	  p.setEducation(eList);
	  
	  p.setUserId(1);
	  r.setData(p);
	  
	  Message m = new Message();
	  m.setInfo("Data successfully saved.");
	  
	  r.setMessage(m);
	  ObjectMapper mapper = new ObjectMapper();
//	  mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
	  System.out.println(mapper.writeValueAsString(r));
	  
	  UserDTO u = new UserDTO();
	  u.setEmail("tamil@tamils.rocks");
	  u.setFirstName("Trish");
	  u.setLastName("Brown");
	  u.setLoginPassword("April@34");
	  
	  mapper = new ObjectMapper();
//	  mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
	  System.out.println(mapper.writeValueAsString(u));
	  
	  
	  u.setEmail(null);
	  u.setFirstName(null);
	  u.setLastName(null);
	  u.setLoginName("pebbles@tamils.rocks");
	  u.setLoginPassword("June@35");
	  
	  mapper = new ObjectMapper();
//	  mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
	  System.out.println(mapper.writeValueAsString(u));
	  
	  
	  
  }
  
  static void deseralize() throws IOException{
    ObjectMapper mapper = new ObjectMapper();
    Country country = new Country("IN", "India");
    System.out.println(mapper.writeValueAsString(country));
    
    String content = "{\"warning\":false,\"error\":false,\"success\":false,\"message\":null,\"code\":\"IN\",\"name\":\"Indiaaa\",\"states\":null},{\"warning\":false,\"error\":false,\"success\":false,\"message\":null,\"code\":\"USA\",\"name\":\"United Stats of America\",\"states\":null}";
    Country cc = mapper.readValue(content, Country.class);
    System.out.println(cc.getName());
    
  }
  static void serializeJRP() throws JsonProcessingException{
    JRPRuleCopyDTO dto = new JRPRuleCopyDTO();
    JRPRuleCopySetDTO set = new JRPRuleCopySetDTO();
    
    dto.setRfstId(11);
    dto.setRfstId(12);
    set.getData().add(dto);
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
//    ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
//    System.out.println(prettyPrinter.writeValueAsString(dto));
    System.out.println(mapper.writeValueAsString(set));
  }
  static void deseralizeState(String data) throws IOException{
    if(data==null || data.length()==0){
      return;
    }
    ObjectMapper mapper = new ObjectMapper();
//    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    State s = mapper.readValue(data, State.class);
    System.out.println("Retrieved country for state is "+s.getCountry().getName());
  }
  
  static void deseralize(String data) throws IOException{
    if(data==null || data.length()==0){
      return;
    }
    ObjectMapper mapper = new ObjectMapper();
    mapper.setDateFormat(new SimpleDateFormat("MM/dd/yyyy"));
//    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    TypeReference<List<Country>> ref = new TypeReference<List<Country>>() {};
    List<Country> countries = mapper.readValue(data, ref);
//    String countryName = 
    System.out.println("Retrieved countries size "+countries.size());
    for(Country country : countries){
      System.out.println("Retrieved country name "+country.getName()+ " Date "+country.getCreatedDate());  
    }
  }
  
  static String callRestPost(){
    StringBuilder data = new StringBuilder();
    try {
      URL url = new URL("http://localhost:8080/ndb/rest/countries/import");
      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
      conn.setRequestMethod("POST");
      conn.setDoOutput(true); //to send request body
//      OutputStream os = conn.getOutputStream();
      
      int code = conn.getResponseCode();
      if(code == HttpURLConnection.HTTP_OK){
        System.out.println("post success");/*
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        
        while((line=reader.readLine())!=null){
          data.append(line);
        }
        System.out.println(data.toString());
        reader.close();
      */}else{
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        String line;
        
        while((line=reader.readLine())!=null){
          data.append(line);
        }
        System.out.println(data.toString());
        reader.close();
        System.out.println("post failed");
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return data.toString();
  }
  static String callRest(){
    StringBuilder data = new StringBuilder();
    try {
      URL url = new URL("http://localhost:8080/ndb/rest/countries");
      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
      conn.setRequestMethod("GET");
      int code = conn.getResponseCode();
      if(code == HttpURLConnection.HTTP_OK){
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        
        while((line=reader.readLine())!=null){
          data.append(line);
        }
        System.out.println(data.toString());
        reader.close();
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return data.toString();
  }
  static String callRestState(){
    StringBuilder data = new StringBuilder();
    try {
      URL url = new URL("http://localhost:8080/ndb/rest/state/TN");
      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
      conn.setRequestMethod("GET");
      int code = conn.getResponseCode();
      if(code == HttpURLConnection.HTTP_OK){
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        
        while((line=reader.readLine())!=null){
          data.append(line);
        }
        System.out.println(data.toString());
        reader.close();
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return data.toString();
  }
  static void parseUsingJsonNode(String data){
    ObjectMapper mapper = new ObjectMapper();
    try {
      JsonNode node = mapper.readTree(data);
      Iterator<JsonNode> itr = node.elements();
      while(itr.hasNext()){
        System.out.println("Name "+itr.next().get("name"));  
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  static void parseStateUsingJsonNode(String data){
    ObjectMapper mapper = new ObjectMapper();
    try {
      JsonNode node = mapper.readTree(data);
      System.out.println(node.get("country"));
//      JsonNode countryNode = mapper.readTree(node.get("country").textValue());
      System.out.println("Country name : "+node.get("country").get("name"));
      Iterator<JsonNode> itr = node.elements();
      while(itr.hasNext()){
        System.out.println("Name "+itr.next().get("name"));  
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  static void writeUsingCustomSerializer(){
    State s = new State();
    s.setId(1);
    s.setCode("TN");
    s.setName("Tamil Nadu");
    s.setCreatedDate(new Date());
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    module.addSerializer(State.class, new JacksonCustomSerializer());
    mapper.registerModule(module);
    try {
      System.out.println(mapper.writeValueAsString(s));
    }
    catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }
}

package com.success.programs;

import java.io.IOException;

import org.appliedjobs.io.user.dtos.Person;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

//@JsonComponent
public class CustomJson {
	public static class Serialize extends JsonSerializer<Person> {
        @Override
        public void serialize(Person value, JsonGenerator jgen, SerializerProvider provider) {
            try {
                if (value == null) {
                    jgen.writeNull();
                }
                else {
                	ObjectMapper mapper = new ObjectMapper();
                    jgen.writeString(mapper.writeValueAsString(value));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static class Deserialize extends JsonDeserializer<Person> {
        @Override
        public Person deserialize(com.fasterxml.jackson.core.JsonParser jp, DeserializationContext ctxt) throws IOException {
            try {
            	JsonNode node = jp.getCodec().readTree(jp);
                String dateAsString = jp.getText();
                if (dateAsString==null) {
                    return null;
                } else {
                	ObjectMapper mapper = new ObjectMapper();
                    return mapper.readValue(dateAsString, Person.class);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

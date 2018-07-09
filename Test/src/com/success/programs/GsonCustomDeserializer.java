package com.success.programs;

import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.success.programs.dto.State;

public class GsonCustomDeserializer implements JsonDeserializer<State>{

  @Override
  public State deserialize(JsonElement je, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
    
    JsonObject jb = je.getAsJsonObject();
    jb.get("").getAsInt();
    
    return null;
  }

}

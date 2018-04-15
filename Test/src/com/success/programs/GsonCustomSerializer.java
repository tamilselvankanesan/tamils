package com.success.programs;

import java.lang.reflect.Type;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.success.programs.dto.State;

public class GsonCustomSerializer implements JsonSerializer<State>{

  @Override
  public JsonElement serialize(State arg0, Type arg1, JsonSerializationContext arg2) {
    JsonObject jb = new JsonObject();
    jb.addProperty("code", arg0.getCode());
    return jb;
  }

}

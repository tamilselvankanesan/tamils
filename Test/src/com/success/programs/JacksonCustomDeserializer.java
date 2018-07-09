package com.success.programs;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.success.programs.dto.State;

public class JacksonCustomDeserializer extends StdDeserializer<State> {
  private static final long serialVersionUID = 1L;

  public JacksonCustomDeserializer() {
    this(null);
  }

  protected JacksonCustomDeserializer(Class<State> vc) {
    super(vc);
  }

  @Override
  public State deserialize(JsonParser jp, DeserializationContext arg1) throws IOException, JsonProcessingException {
    State s = new State();
    JsonNode node = jp.getCodec().readTree(jp);
    s.setId(((IntNode)node.get("id")).intValue());
    return s;
  }

}

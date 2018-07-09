package com.success.programs;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.success.programs.dto.State;

public class JacksonCustomSerializer extends StdSerializer<State> {
  
  private static final long serialVersionUID = 1L;

  public JacksonCustomSerializer(){
    this(null);
  }
  protected JacksonCustomSerializer(Class<State> t) {
    super(t);
  }

  @Override
  public void serialize(State state, JsonGenerator jg, SerializerProvider arg2) throws IOException {
    
    jg.writeStartObject();
    jg.writeNumberField("stateid", state.getId());
    jg.writeStringField("state_code", state.getCode());
    jg.writeStringField("date", new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
    jg.writeEndObject();
    
  }

}

package com.mypocket.storeManagement.utilities.converters;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class CustomDoubleConverter extends StdDeserializer<Double> {

    public CustomDoubleConverter() {
        this(null);
    }

    public CustomDoubleConverter(Class<?> vc) {
        super(vc);
    }

    @Override
    public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        return Double.parseDouble(jsonParser.getText().replace(",", "."));
    }
}

package com.mypocket.storeManagement.utilities.converters;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.mypocket.storeManagement.utilities.ReceiptDate;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class CustomDateTimeConverter extends StdDeserializer<LocalDateTime> {



    public CustomDateTimeConverter() {
        this(null);
    }

    public CustomDateTimeConverter(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        String time = jsonParser.getText();

        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));


//        ReceiptDate receiptDate = new ReceiptDate();
//
//        receiptDate.setDate(date.toLocalDate().toString());
//        receiptDate.setTime(date.toLocalTime().toString());
//
//        return receiptDate;

    }
}

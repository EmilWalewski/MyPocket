package com.mypocket.storeManagement.utilities;

import javax.persistence.AttributeConverter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReceiptDateConverter implements AttributeConverter<ReceiptDate, LocalDateTime> {

    @Override
    public LocalDateTime convertToDatabaseColumn(ReceiptDate receiptDate) {


        return LocalDateTime.of(LocalDate.parse(receiptDate.getDate()), LocalTime.parse(receiptDate.getTime()));
    }

    @Override
    public ReceiptDate convertToEntityAttribute(LocalDateTime localDateTime) {

        ReceiptDate receiptDate = new ReceiptDate();


        String month = localDateTime.getMonthValue() < 10 ? "0"+localDateTime.getMonthValue() : ""+localDateTime.getMonthValue();
        String day = localDateTime.getDayOfMonth() < 10 ? "0"+localDateTime.getDayOfMonth() : ""+localDateTime.getDayOfMonth();

        receiptDate.setDate(localDateTime.getYear() + "-" + month + "-" + day);

        receiptDate.setTime(localDateTime.getHour()+":"+localDateTime.getMinute());

        return receiptDate;
    }
}

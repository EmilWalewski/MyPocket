package com.mypocket.storeManagement.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.mypocket.storeManagement.utilities.CustomDateTimeSerializer;
import com.mypocket.storeManagement.utilities.ReceiptDate;
import com.mypocket.storeManagement.utilities.ReceiptDateConverter;
import com.mypocket.storeManagement.utilities.converters.CustomDateTimeConverter;
import com.mypocket.storeManagement.utilities.converters.CustomDoubleConverter;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "receipt")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column(name = "shopping_date")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    private LocalDate date;
//
//    @Column(name = "shopping_time")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm:ss")
//
//    private LocalTime time;

    @Column(name = "shop_name", length = 30)
    private String shop_name;

    @Column(name = "shopping_price", length = 4, precision = 2)
    @JsonDeserialize(using = CustomDoubleConverter.class)
    private double price;

//    @Column(name = "shopping_date")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
//    @JsonDeserialize(using = CustomDateTimeConverter.class)
//    @Convert(converter = ReceiptDateConverter.class)
//    private ReceiptDate receiptDate;

    @JsonDeserialize(using = CustomDateTimeConverter.class)
    @JsonSerialize(using = CustomDateTimeSerializer.class)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime shopping_date;

    @OneToOne(targetEntity = Photo.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "photo_id")
    @JsonIgnore
    private Photo receipt_photo;

    private transient String encodedImage;

    private transient String userName;

    @JsonIgnore
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Receipt() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getShopping_date() {
        return shopping_date;
    }

    public void setShopping_date(LocalDateTime shopping_date) {
        this.shopping_date = shopping_date;
    }

    public Photo getReceipt_photo() {
        return receipt_photo;
    }

    public void setReceipt_photo(Photo receipt_photo) {
        this.receipt_photo = receipt_photo;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

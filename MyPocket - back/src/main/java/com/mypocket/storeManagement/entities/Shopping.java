package com.mypocket.storeManagement.entities;

import com.mypocket.storeManagement.models.ShoppingModel;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

//@Entity
//@Table
public class Shopping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "shopping_date")
    private LocalDate localDate;

    @ElementCollection
    private List<Product> productList;


    public Shopping() {
    }

    public Shopping(ShoppingModel shoppingModel) {
        this.id = shoppingModel.getId();
        this.localDate = shoppingModel.getShopping_date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

        public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}

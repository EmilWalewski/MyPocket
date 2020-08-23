package com.mypocket.storeManagement.models;

import com.mypocket.storeManagement.entities.Product;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

public class ShoppingModel {

    private int id;

    private LocalDate shopping_date;

    @Valid
    private List<ProductModel> productList;

    public ShoppingModel() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getShopping_date() {
        return shopping_date;
    }

    public void setShopping_date(LocalDate shopping_date) {
        this.shopping_date = shopping_date;
    }

    public List<ProductModel> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductModel> productList) {
        this.productList = productList;
    }
}

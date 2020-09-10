package com.mypocket.storeManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mypocket.storeManagement.models.ProductModel;

import javax.persistence.*;

@Embeddable
public class Product {

    @Column(name = "product_name", length = 100, nullable = false)
    private String productName;

    @Column(name = "product_price")
    private double price;

    @OneToOne(targetEntity = SubCategory.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "sub_category_id", referencedColumnName = "id")
    @JsonIgnore
    private SubCategory subCategory;


    private transient int sub_category_id;


    public Product() {
    }

    public Product(ProductModel productModel) {
        this.productName = productModel.getProductName();
        this.price = productModel.getPrice().doubleValue();
        this.sub_category_id = productModel.getSub_category_id();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public int getSub_category_id() {
        return sub_category_id;
    }

    public void setSub_category_id(int sub_category_id) {
        this.sub_category_id = sub_category_id;
    }
}

package com.mypocket.storeManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

//@Entity
//@Table
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sub_category_name", length = 60, unique = true, nullable = false)
    private String subCategoryName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private ProductCategory category;

    public SubCategory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "id=" + id +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", category=" + category +
                '}';
    }
}

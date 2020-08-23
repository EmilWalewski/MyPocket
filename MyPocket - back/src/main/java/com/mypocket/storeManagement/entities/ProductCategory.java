package com.mypocket.storeManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mypocket.storeManagement.models.ProductCategoryModel;

import javax.persistence.*;
import java.util.List;

//@Entity
//@Table
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "category_name", length = 30, unique = true, nullable = false)
    private String name;

    @OneToMany(targetEntity = SubCategory.class, mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SubCategory> subCategoryList;

    public ProductCategory() {
    }

    public ProductCategory(ProductCategoryModel productCategoryModel) {
        this.id = productCategoryModel.getId();
        this.name = productCategoryModel.getName();
        this.subCategoryList = productCategoryModel.getSubCategoryList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubCategory> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(List<SubCategory> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

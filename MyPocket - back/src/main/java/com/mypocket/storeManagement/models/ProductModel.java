package com.mypocket.storeManagement.models;

import com.mypocket.annotations.PriceValidator;
import com.mypocket.storeManagement.entities.SubCategory;
import com.mypocket.storeManagement.utilities.ProductUtilities;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

public class ProductModel {

    @Pattern(regexp = ProductUtilities.PRODUCT_NAME, message = "Invalid product name")
    private String productName;

    @PriceValidator(message = "Price is invalid")
    private BigDecimal price;

    private int sub_category_id;

    public ProductModel() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getSub_category_id() {
        return sub_category_id;
    }

    public void setSub_category_id(int sub_category_id) {
        this.sub_category_id = sub_category_id;
    }
}

package com.mypocket.storeManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mypocket.annotations.PriceValidator;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "wallet_name", length = 30)
    @Pattern(regexp = "^[A-Z][A-Za-z0-9]{2,20}", message = "Incorrect wallet name")
    private String name;

    @Column(name = "wallet_quantity", length = 5, precision = 2)
    @PriceValidator
    private Double quantity;

    @Column(name = "is_main_wallet")
    @JsonProperty("isMainWallet")
    private boolean isMainWallet;

    @JsonIgnore
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Wallet() {
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

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public boolean isMainWallet() {
        return isMainWallet;
    }

    public void setMainWallet(boolean mainWallet) {
        isMainWallet = mainWallet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

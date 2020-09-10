package com.mypocket.storeManagement.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name", length = 20, nullable = false, unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active", length = 1)
    private int isActive;

<<<<<<< HEAD
<<<<<<< HEAD
    @OneToMany(targetEntity = Wallet.class, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Wallet> wallets;
=======
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
=======
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
<<<<<<< HEAD
<<<<<<< HEAD

    public List<Wallet> getWallets() {
        return wallets;
    }

    public void setWallets(List<Wallet> wallets) {
        this.wallets = wallets;
    }
=======
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
=======
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
}

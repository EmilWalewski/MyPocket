package com.mypocket.storeManagement.entities;

import java.util.List;

public class WalletWrapper {

    List<Wallet> wallets;

    public WalletWrapper() {
    }

    public List<Wallet> getWallets() {
        return wallets;
    }

    public void setWallets(List<Wallet> wallets) {
        this.wallets = wallets;
    }
}

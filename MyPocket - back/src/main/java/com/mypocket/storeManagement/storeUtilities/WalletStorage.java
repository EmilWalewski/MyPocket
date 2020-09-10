package com.mypocket.storeManagement.storeUtilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mypocket.storeManagement.entities.Wallet;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class WalletStorage {

    @PersistenceContext(unitName = "mySqlFactory")
    private EntityManager entityManager;

    @Transactional
    public boolean saveWallet(Wallet wallet){

//        entityManager.persist(wallet);

        return true;

    }
}

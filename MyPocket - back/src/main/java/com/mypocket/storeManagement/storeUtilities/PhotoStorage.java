package com.mypocket.storeManagement.storeUtilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mypocket.storeManagement.entities.Photo;
import com.mypocket.storeManagement.entities.Receipt;
<<<<<<< HEAD
//import org.json.JSONObject;
=======
import org.json.JSONObject;
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoStorage {

    @PersistenceContext(unitName = "mySqlFactory")
    private EntityManager sqlEntityManager;


    @Transactional
    public int saveImage(MultipartFile photo){

        try {
            sqlEntityManager.persist(new Photo(photo.getOriginalFilename(), photo.getContentType(), photo.getBytes()));
            return getLastPersistedImageId();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Transactional
    public int saveReceipt(Receipt receipt){



        if (receipt.getId() == 0){
            sqlEntityManager.persist(receipt);
            return getLastPersistedReceiptRecord();
        }
        else{
//            sqlEntityManager.merge(receipt);
           sqlEntityManager.createNativeQuery("update receipt set shopping_date = ?1 where id = ?2")
                    .setParameter(1, receipt.getShopping_date())
                    .setParameter(2, receipt.getId()).executeUpdate();

            return receipt.getId();
        }


    }

    public String getReceipt(int id){

        try {


             Receipt receipt = (Receipt) sqlEntityManager.createQuery("from Receipt where id = ?1")
                             .setParameter(1, id)
                             .getResultList().stream().findFirst().get();

             String encodeImage = Base64.getEncoder().encodeToString(receipt.getReceipt_photo().getData());

             receipt.setEncodedImage(encodeImage);

             return new ObjectMapper().writeValueAsString(receipt);

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return null;
    }

    public String getReceipts(){

        try {


            return new ObjectMapper().writeValueAsString(
                    sqlEntityManager.createQuery("from Receipt", Receipt.class)
                    .getResultStream()
                    .map(receipt -> {
                        receipt.setEncodedImage(Base64.getEncoder().encodeToString(receipt.getReceipt_photo().getData()));
                        return receipt;
                    }).collect(Collectors.toList())
            );


        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return null;
    }

    private int getLastPersistedReceiptRecord(){

        try {
            return (int)sqlEntityManager.createNativeQuery("select id from receipt order by id desc limit 1").getSingleResult();

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return 0;
    }

    private int getLastPersistedImageId(){

        try {

        return (int)sqlEntityManager.createNativeQuery("select top id from receipt_images order by id desc limit 1").getResultList()
                .stream()
                .findFirst()
                .orElseThrow(() -> new Exception("internal error"));

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return 0;
    }
}

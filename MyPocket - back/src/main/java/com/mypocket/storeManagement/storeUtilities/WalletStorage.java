package com.mypocket.storeManagement.storeUtilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mypocket.storeManagement.entities.User;
import com.mypocket.storeManagement.entities.Wallet;
import com.mypocket.storeManagement.entities.WalletWrapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletStorage {

    @PersistenceContext(unitName = "mySqlFactory")
    private EntityManager entityManager;

    @Autowired
    private UserStore userStore;

    @Transactional
    public int saveWallet(Wallet wallet, String username){

        wallet.setUser(userStore.findUserByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Internal Error: User not found")));

        if (wallet.getId() == 0){

            entityManager.persist(wallet);
            return 0;
        }
        else{

            entityManager.createNativeQuery("update wallet set wallet_name = ?1, wallet_quantity = ?2 where id = ?3")
                    .setParameter(1, wallet.getName())
                    .setParameter(2, wallet.getQuantity())
                    .setParameter(3, wallet.getId()).executeUpdate();

            return wallet.getId();
        }

    }

    @Transactional
    public Object saveWallets(List<Wallet> walletList, String header) {

//        User user = userStore.findUserByUserName(header).orElseThrow(() -> new UsernameNotFoundException("Internal Error: User not found"));

        User user = entityManager.createQuery(
                "select p " +
                        "from User p " +
                        "join fetch p.wallets " +
                        "where p.username = ?1", User.class)
                .setParameter(1, header)
                .getSingleResult();

        entityManager.detach(user);

        user.getWallets().clear();

        for (Wallet wallet: walletList) {
            wallet.setUser(user);
            user.getWallets().add(wallet);

        }

        entityManager.merge(user);


        return header;
    }

    public String getWallets(String username){

        try {

            return new ObjectMapper().writeValueAsString(
                    entityManager.createQuery("from Wallet where user = ?1", Wallet.class)
                            .setParameter(1, userStore.findUserByUserName(username).get())
                            .getResultStream()
                            .collect(Collectors.toList())
            );


        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return null;
    }

}

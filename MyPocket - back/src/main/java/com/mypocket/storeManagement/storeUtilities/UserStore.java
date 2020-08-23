package com.mypocket.storeManagement.storeUtilities;

import com.mypocket.storeManagement.entities.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
public class UserStore {

    @PersistenceContext(unitName = "mySqlFactory")
    private EntityManager sqlEntityManager;

    public Optional<User> findUserByUserName(String username){

        try {

            return Optional.of(sqlEntityManager.createQuery("from User where username = ?1", User.class)
                    .setParameter(1, username)
                    .getSingleResult());

        } catch (Exception e) {
            //logs
        }

        return Optional.empty();

    }
}

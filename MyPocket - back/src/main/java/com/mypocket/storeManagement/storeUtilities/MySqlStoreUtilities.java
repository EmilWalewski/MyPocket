package com.mypocket.storeManagement.storeUtilities;

import com.mypocket.errors.ResourceNotFoundException;
import com.mypocket.security.userConfiguration.PrincipalDetailsService;
import com.mypocket.storeManagement.entities.*;
import com.mypocket.storeManagement.models.ShoppingModel;
<<<<<<< HEAD
//import org.json.JSONObject;
=======
import org.json.JSONObject;
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.lang.ref.WeakReference;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MySqlStoreUtilities {

    @PersistenceContext(unitName = "mySqlFactory")
    private EntityManager sqlEntityManager;

<<<<<<< HEAD
//    private JSONObject globalJsonObject;
=======
    private JSONObject globalJsonObject;
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59

    @Transactional
    public String createShopping(ShoppingModel shoppingModel){

        List<SubCategory> subCategoryList = getAllSubCategories();

        Shopping shopping = new Shopping(shoppingModel);
        shopping.setProductList(shoppingModel.getProductList().stream().map(Product::new).collect(Collectors.toList()));

<<<<<<< HEAD
//        globalJsonObject = new JSONObject();
=======
        globalJsonObject = new JSONObject();
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59

        shopping.getProductList().forEach(product ->

                product.setSubCategory(subCategoryList
                        .stream()
                        .filter(subCategory ->
                            subCategory.getId() == product.getSub_category_id())
                        .findFirst()
                        .orElseThrow(() -> new ResourceNotFoundException("Resource can not be found")))
        );


        sqlEntityManager.persist(shopping);
<<<<<<< HEAD
//        globalJsonObject.put("message", "Shopping information saved successfully");

//        return globalJsonObject.toString();
        return null;
=======
        globalJsonObject.put("message", "Shopping information saved successfully");

        return globalJsonObject.toString();
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
    }

    @SuppressWarnings("unchecked")
    public List<ProductCategory> getAllCategories(){

        return sqlEntityManager.createQuery("from ProductCategory").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<SubCategory> getAllSubCategories(){

        return sqlEntityManager.createQuery("from SubCategory").getResultList();
    }

    public SubCategory getSubCategoryById(int id){

        try {

            return sqlEntityManager.createQuery("from SubCategory where id = ?1", SubCategory.class)
                    .setParameter(1, id)
                    .getSingleResult();

        } catch (Exception e) {
            throw new ResourceNotFoundException("Subcategory can not be found");
        }
    }


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

    @Autowired
    PasswordEncoder passwordEncoder;


    @Transactional
    public void createUser() {

        User user = new User();
<<<<<<< HEAD
        user.setUsername("b");
        user.setPassword(passwordEncoder.encode("b"));
=======
        user.setUsername("user1");
        user.setPassword(passwordEncoder.encode("pass1"));
>>>>>>> 9e6d022973377bf9283ae4cf365c8311ec811e59
        user.setIsActive(1);

        sqlEntityManager.persist(user);
    }




    /*
    *
    *
    *                   init method
    *
    *
    *
    *
    * */
    @Transactional
    public void init(){

        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("Meat");


        SubCategory subCategory1 = new SubCategory();
        subCategory1.setSubCategoryName("Sausages");

        SubCategory subCategory2 = new SubCategory();
        subCategory2.setSubCategoryName("Chicken");

        SubCategory subCategory3 = new SubCategory();
        subCategory3.setSubCategoryName("Turkey");

        subCategory1.setCategory(productCategory);
        subCategory2.setCategory(productCategory);
        subCategory3.setCategory(productCategory);

        productCategory.setSubCategoryList(Arrays.asList(subCategory1, subCategory2, subCategory3));

        sqlEntityManager.persist(productCategory);

        /*
         *
         *           save shopping
         *
         *
         */
//        Shopping shopping = new Shopping();
//
//        shopping.setLocalDate(LocalDate.now());
//
//        Product prod1 = new Product();
//        prod1.setProductName("aaa");
//        prod1.setPrice(3);
//        prod1.setSubCategory(subCategory2);
//
//        Product prod2 = new Product();
//        prod2.setProductName("bbb");
//        prod2.setPrice(5);
//        prod1.setSubCategory(subCategory1);
//
//        Product prod3 = new Product();
//        prod3.setProductName("ccc");
//        prod3.setPrice(7);
//        prod1.setSubCategory(subCategory1);
//
//        shopping.setProductList(Arrays.asList(prod1, prod2, prod3));
//
//        sqlEntityManager.persist(shopping);

    }

}

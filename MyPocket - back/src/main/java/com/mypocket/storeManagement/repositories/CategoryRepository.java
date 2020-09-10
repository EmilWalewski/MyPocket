package com.mypocket.storeManagement.repositories;

import com.mypocket.storeManagement.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<ProductCategory, Integer>{
}

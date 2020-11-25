package com.sda.shop.repository;

import com.sda.shop.entity.ProductCategoryEntity;
import com.sda.shop.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity,Integer> {
}

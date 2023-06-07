package com.example.myshop.repository;

import com.example.myshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByCategoryId(int id);
}

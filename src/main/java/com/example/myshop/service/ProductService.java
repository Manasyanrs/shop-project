package com.example.myshop.service;

import com.example.myshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<Product> getProducts(Pageable pageable);

    Product getProductById(int id);
    void saveProduct(Product product);
    void deleteById(int id);

    List<Product> getProductByCategories(int chosenCategory);

    List<Product> getProductList();
}

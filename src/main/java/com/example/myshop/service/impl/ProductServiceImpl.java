package com.example.myshop.service.impl;

import com.example.myshop.entity.Product;
import com.example.myshop.repository.ProductRepository;
import com.example.myshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductByCategories(int chosenCategory) {
        return productRepository.findAllByCategoryId(chosenCategory);
    }

    @Override
    public List<Product> getProductList() {
        return productRepository.findAll();
    }
}

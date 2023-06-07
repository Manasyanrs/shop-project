package com.example.myshop.service;

import com.example.myshop.entity.Cart;

import java.util.Optional;

public interface CartService {
    void addCart(Cart cart);

    Optional<Cart> getCartByUserId(int userId);

    int totalCost(Cart cart);

    void addProducts(Cart cart);

    void deleteProductById(Cart cart, int id);

}

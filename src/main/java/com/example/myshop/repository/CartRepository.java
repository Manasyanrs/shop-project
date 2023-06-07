package com.example.myshop.repository;

import com.example.myshop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Optional<Cart> findCartByUserId(int userId);

}

package com.example.myshop.service;

import com.example.myshop.entity.Order;

import java.util.List;

public interface OrderService {
    void save(Order order);

    List<Order> getOrders();

}

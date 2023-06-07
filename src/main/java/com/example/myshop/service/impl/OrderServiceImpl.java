package com.example.myshop.service.impl;

import com.example.myshop.entity.Order;
import com.example.myshop.repository.OrderRepository;
import com.example.myshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

}

package com.example.myshop.controller;

import com.example.myshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final OrderService orderService;

    @GetMapping("orders")
    public String getOrdersList(ModelMap modelMap) {
        modelMap.addAttribute("orders", orderService.getOrders());
        return "ordersList";
    }
}

package com.example.demo.services;

import com.example.demo.models.Order;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrderService {

    public List<Order> findAll();

    public Order findById(int id);

    public Order addOrder(Order o);

    public boolean delete(int id);

    public String placeOrder();
}
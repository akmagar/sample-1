package com.example.demo.services;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.Order;
import com.example.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderDAO;

    @Override
    public List<Order> findAll(){
        return orderDAO.findAll();
    }

    @Override
    public Order findById(int id) {
        return orderDAO.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("No order with id = %id", id)));
    }

    @Override
    public Order addOrder(Order o) {
        orderDAO.save(o);
        return o;

    }


    @Override
    public boolean delete(int id) {
        if(!orderDAO.existsById(id)) {
            return false;
        }
        orderDAO.deleteById(id);
        return true;
    }

    @Override
    public String placeOrder() {
        return "You're placing your oder";
    }


}
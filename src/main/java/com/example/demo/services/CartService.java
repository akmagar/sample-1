package com.example.demo.services;

import com.example.demo.models.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    public List<Cart> findAll();

    public Cart findById(int id);

    public Cart addCart(Cart c);

    public Cart update(Cart c);

    public boolean delete(int id);

    public String checkOut();
}

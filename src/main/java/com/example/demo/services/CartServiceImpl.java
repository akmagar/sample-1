package com.example.demo.services;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.Cart;
import com.example.demo.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartDAO;

    @Override
    public List<Cart> findAll() {
        return cartDAO.findAll();
    }

    @Override
    public Cart findById(int id) {
        return cartDAO.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("No cart with id = %id", id)));
    }

    @Override
    public Cart addCart(Cart c) {
        cartDAO.save(c);
        return c;

    }

    @Override
    public Cart update(Cart c) {
        if (cartDAO.existsById(c.getId())) {
            throw new RuntimeException("Cart must already exist to update");
        }
        cartDAO.save(c);
        return c;
    }

    @Override
    public boolean delete(int id) {
        if(!cartDAO.existsById(id)) {
            return false;
        }
        cartDAO.deleteById(id);
        return true;
    }

    @Override
    public String checkOut() {
        return "You're checking out";

    }

}
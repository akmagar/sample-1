package com.example.demo.controllers;

import com.example.demo.annotations.Authorized;
import com.example.demo.models.Cart;
import com.example.demo.models.Role;
import com.example.demo.services.AuthorizationService;
import com.example.demo.services.CartService;
import com.example.demo.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ItemService itemService;
    @Autowired
    private AuthorizationService authorizationService;

    @Authorized(allowedRoles = {Role.ADMIN, Role.EMPLOYEE})
    @GetMapping
    public ResponseEntity<List<Cart>> findAll() {
        return ResponseEntity.ok(cartService.findAll());
    }

    @Authorized(allowedRoles = {Role.ADMIN, Role.EMPLOYEE})
    @GetMapping("/{id}")
    public ResponseEntity<Cart> findById(@PathVariable("id") int id) {
        return ResponseEntity.ok(cartService.findById(id));
    }

    @Authorized(allowedRoles = {Role.CUSTOMER})
    @PostMapping("/checkout")
    public String home() {
        return cartService.checkOut();
    }

    @Authorized(allowedRoles = {Role.CUSTOMER})
    @PostMapping
    public ResponseEntity<Cart> addCart(@RequestBody Cart c) {
        return ResponseEntity.accepted().body(cartService.addCart(c));
    }

    @Authorized(allowedRoles = {Role.CUSTOMER})
    @PutMapping
    public ResponseEntity<Cart> update(@RequestBody Cart c) {
        return ResponseEntity.accepted().body(cartService.update(c));
    }

    @Authorized(allowedRoles = {Role.CUSTOMER})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        if(cartService.delete(id)) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.noContent().build();
    }

}

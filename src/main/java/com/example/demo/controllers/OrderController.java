package com.example.demo.controllers;

import com.example.demo.annotations.Authorized;
import com.example.demo.models.*;
import com.example.demo.services.AuthorizationService;
import com.example.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthorizationService authorizationService;

    @Authorized(allowedRoles = {Role.ADMIN, Role.EMPLOYEE})
    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @Authorized(allowedRoles = {Role.ADMIN, Role.EMPLOYEE})
    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable("id") int id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @Authorized(allowedRoles = {Role.CUSTOMER})
    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order o) {
        return ResponseEntity.accepted().body(orderService.addOrder(o));
    }

    @Authorized(allowedRoles = {Role.ADMIN, Role.EMPLOYEE, Role.CUSTOMER})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        if(orderService.delete(id)) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.noContent().build();
    }

}

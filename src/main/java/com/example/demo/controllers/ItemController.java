package com.example.demo.controllers;

import com.example.demo.annotations.Authorized;
import com.example.demo.models.Item;
import com.example.demo.models.Role;
import com.example.demo.services.AuthorizationService;
import com.example.demo.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private AuthorizationService authorizationService;

    @GetMapping
    public ResponseEntity<List<Item>> findAll() {
        return ResponseEntity.ok(itemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> findById(@PathVariable("id") int id) {
        return ResponseEntity.ok(itemService.findById(id));
    }

    @Authorized(allowedRoles = {Role.ADMIN, Role.EMPLOYEE})
    @PostMapping
    public ResponseEntity<Item> addItem(@RequestBody Item i) {
        return ResponseEntity.accepted().body(itemService.addItem(i));
    }

    @Authorized(allowedRoles = {Role.ADMIN, Role.EMPLOYEE})
    @PutMapping
    public ResponseEntity<Item> update(@RequestBody Item i) {
        return ResponseEntity.accepted().body(itemService.update(i));
    }

    @Authorized(allowedRoles = {Role.ADMIN, Role.EMPLOYEE})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        if(itemService.delete(id)) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.noContent().build();
    }
}

package com.example.demo.controllers;

import com.example.demo.annotations.Authorized;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.services.AuthorizationService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizationService authorizationService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Authorized(allowedRoles = {Role.ADMIN, Role.EMPLOYEE})
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") int id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User u) {
        return ResponseEntity.accepted().body(userService.registerUser(u));
    }

    @PutMapping
    @Authorized(allowedRoles = {Role.ADMIN, Role.EMPLOYEE, Role.CUSTOMER})
    public ResponseEntity<User> update(@RequestBody User u) {
        return ResponseEntity.accepted().body(userService.update(u));
    }

    @Authorized(allowedRoles = {Role.ADMIN, Role.EMPLOYEE})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        if(userService.delete(id)) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.noContent().build();
    }
}

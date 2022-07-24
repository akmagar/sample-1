package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Table(name = "carts", schema = "public")
@Data @AllArgsConstructor @NoArgsConstructor
public class Cart{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int item_id;
    private int user_id;
    private int total;
    private int item_quantity;

}

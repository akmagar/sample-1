package com.example.demo.services;

import com.example.demo.models.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ItemService {

    public Optional<Item> findByItemName(String itemName);

    public List<Item> findAll();

    public Item findById(int id);

    public Item addItem(Item i);

    public Item update(Item i);

    public boolean delete(int id);


    Optional<Item> getItemById(int id);
}

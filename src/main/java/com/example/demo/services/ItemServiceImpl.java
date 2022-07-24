package com.example.demo.services;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.Item;
import com.example.demo.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemRepository itemDAO;

    @Override
    public Optional<Item> findByItemName(String itemName) {
        return Optional.empty();
    }

    public List<Item> findAll() {
        return itemDAO.findAll();
    }

    public Item findById(int id) {
        return itemDAO.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("No item with id = %d", id)));
    }

    public Item addItem(Item i) {
        itemDAO.save(i);
        return i;
    }

    public Item update(Item i) {
        if (!itemDAO.existsById(i.getId())) {
            throw new RuntimeException("Item must already exist to update");
        }
        itemDAO.save(i);
        return i;
    }

    public boolean delete(int id) {
        if (!itemDAO.existsById(id)) {
            return false;
        }
        itemDAO.deleteById(id);
        return true;
    }

    @Override
    public Optional<Item> getItemById(int id) {
        return itemDAO.findById(id);
    }
}


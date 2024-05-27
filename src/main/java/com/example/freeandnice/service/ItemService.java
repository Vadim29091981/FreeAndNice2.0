package com.example.freeandnice.service;

import com.example.freeandnice.exceptions.ResourceNotFoundException;
import com.example.freeandnice.model.Item;
import com.example.freeandnice.model.User;
import com.example.freeandnice.repository.ItemRepository;
import com.example.freeandnice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(int id) {
        return itemRepository.findById(id);
    }




    public Item createItem(Item item, int userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        item.setUser(user);
        return itemRepository.save(item);
    }
    public Item updateItem(int id, Item itemDetails) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            item.setTitle(itemDetails.getTitle());
            item.setUser(itemDetails.getUser());
            item.setAuction(itemDetails.getAuction());
            // Сохраняем обновленный предмет
            return itemRepository.save(item);
        } else {
            // Обработка случая, когда предмет не найден
            return null;
        }
    }

    public void deleteItem(int id) {
        itemRepository.deleteById(id);
    }
}
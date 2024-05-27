package com.example.freeandnice;


import com.example.freeandnice.exceptions.ResourceNotFoundException;
import com.example.freeandnice.model.Item;
import com.example.freeandnice.model.User;
import com.example.freeandnice.repository.ItemRepository;
import com.example.freeandnice.repository.UserRepository;
import com.example.freeandnice.service.ItemService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ItemTest {


    @Mock
    private ItemRepository itemRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    public void testCreateItem() throws ResourceNotFoundException {
        Item item = new Item();
        item.setId(1);
        User user = new User();
        user.setId(1);

        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        when(itemRepository.save(item)).thenReturn(item);

        Item result = itemService.createItem(item, 1);

        assertEquals(1, result.getId());
        assertEquals(1, result.getUser().getId());
    }

    @Test
    public void testGetAllItems() {
        Item item1 = new Item();
        Item item2 = new Item();
        List<Item> items = Arrays.asList(item1, item2);

        when(itemRepository.findAll()).thenReturn(items);

        List<Item> result = itemService.getAllItems();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetItemById() {
        Item item = new Item();
        item.setId(1);

        when(itemRepository.findById(1)).thenReturn(Optional.of(item));

        Optional<Item> result = itemService.getItemById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }
}
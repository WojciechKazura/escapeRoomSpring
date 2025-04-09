package com.escapeRoom.repository;

import com.escapeRoom.entity.Item;
import com.escapeRoom.entity.Window;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")  // jeśli masz osobny plik application-test.properties
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void shouldSaveAndFindItemById() {
        Item window = new Window();
        window.setName("Test Window");
        Item saved = itemRepository.save(window);
        Optional<Item> found = itemRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("Test Window", found.get().getName());
    }

    @Test
    void shouldReturnEmptyWhenItemNotFound() {
        Optional<Item> item = itemRepository.findById(999);
        assertTrue(item.isEmpty());
    }

    @Test
    void shouldFindAllItems() {
        Item item1 = new Window();
        item1.setName("Item1");

        Item item2 = new Window();
        item2.setName("Item2");

        itemRepository.save(item1);
        itemRepository.save(item2);

        var allItems = itemRepository.findAll();
        assertEquals(2, allItems.size());
    }
}


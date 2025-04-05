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
        // given
        Item window = new Window(); // lub: new Item(); jeśli Item nie jest abstrakcyjne
        window.setName("Test Window");

        // when
        Item saved = itemRepository.save(window);
        Optional<Item> found = itemRepository.findById(saved.getId());

        // then
        assertTrue(found.isPresent());
        assertEquals("Test Window", found.get().getName());
    }

    @Test
    void shouldReturnEmptyWhenItemNotFound() {
        // when
        Optional<Item> item = itemRepository.findById(999);

        // then
        assertTrue(item.isEmpty());
    }

    @Test
    void shouldFindAllItems() {
        // given
        Item item1 = new Window();
        item1.setName("Item1");

        Item item2 = new Window();
        item2.setName("Item2");

        itemRepository.save(item1);
        itemRepository.save(item2);

        // when
        var allItems = itemRepository.findAll();

        // then
        assertEquals(2, allItems.size());
    }
}


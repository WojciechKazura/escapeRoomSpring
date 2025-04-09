package com.escapeRoom.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTypeTest {

    @Test
    void testAllEnumValues_ShouldNotBeNull() {
        for (ItemType type : ItemType.values()) {
            assertNotNull(type);
        }
    }

    @Test
    void testValueOf_ShouldReturnCorrectEnum() {
        ItemType type = ItemType.valueOf("KEY");
        assertEquals(ItemType.KEY, type);
    }

    @Test
    void testEnumContainsSpecificValues() {
        assertTrue(containsEnum("FLASHLIGHT"));
        assertTrue(containsEnum("FURNITURE"));
        assertFalse(containsEnum("NON_EXISTENT_TYPE"));
    }

    private boolean containsEnum(String name) {
        try {
            ItemType.valueOf(name);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
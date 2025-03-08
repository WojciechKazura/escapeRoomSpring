package com.escapeRoom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Book extends Item {

    @Getter
    private String title;
    @Getter
    private String author;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "furniture_for_books_id")
    private FurnitureForBooks furnitureForBooks;

    @Getter
    @Setter
    private int bookPosition;

    @Getter
    @Setter
    private boolean placedCorrectly = false;

    public Book() {
    }

    public Book(String title, String author, int bookPosition) {
        super("Książka o tytule " + title + ". pozycja" + bookPosition, ItemType.BOOK);
        this.title = title;
        this.author = author;
        this.bookPosition = bookPosition;
    }

    public String use(Context context) {
        if (furnitureForBooks != null) {
            return furnitureForBooks.placeBook(this, context);  // Wywołanie metody w regale
        }
        return "Nie możesz teraz użyć tej książki.";
    }
}

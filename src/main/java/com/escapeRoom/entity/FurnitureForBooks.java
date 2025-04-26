package com.escapeRoom.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Entity
public class FurnitureForBooks extends Item {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "furniture_for_books_id")
    private List<Book> bookList;
    @Getter
    @ManyToOne
    private Container container;
    @Getter
    @ManyToOne
    private Item item;


    private boolean firstUse = true;

    private int answer = 1;

    private boolean correctBooksCode = false;

    public FurnitureForBooks() {
    }

    public FurnitureForBooks(List<Book> bookList, Container container, Item item) {
        super("Regał z książkami", ItemType.FURNITURE);
        this.bookList = new ArrayList<>(bookList);
        this.container = container;
        this.item = item;
    }

    public String placeBook(Book book, Context context) {
        if (bookList.contains(book)) {
            int expectedPosition = book.getBookPosition();
            if (expectedPosition == answer) {
                book.setPlacedCorrectly(true);
                context.getRoom().getItemList().remove(book);
                answer++;
                return "Książka '" + book.getTitle() + "' została poprawnie odłożona na półkę.";
            } else {
                return "Zła pozycja! Książka '" + book.getTitle() + "' powinna być na miejscu nr " + expectedPosition;
            }
        } else {
            return "Nie można odłożyć tej książki na ten regał!";
        }
    }

    private boolean checkCode() {
        for (Book book : bookList) {
            if (!book.isPlacedCorrectly()) {
                correctBooksCode = false;
                break;
            } else {
                correctBooksCode = true;
            }
        }
        return correctBooksCode;
    }


    @Override
    public String use(Context context) {
        List<Item> itemList = context.getRoom().getItemList();
        if (firstUse) {
            itemList.addAll(bookList);
            firstUse = false;
            return "Zdejmujesz z półki " + bookList.size() + " książki.";
        } else if (checkCode()) {
            context.getRoom().getItemList().add(container);
            context.getRoom().getItemList().add(item);
            return "Udało się";
        } else {
            return "Próbuj dajej rozwiązać zagadke z książkami.";
        }
    }


}




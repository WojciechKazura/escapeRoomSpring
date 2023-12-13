package com.escapeRoom.dto;

public class ItemDto {
    //json będą mapowane na ten obiekt i z tego obiektu na jsony
    //dzieje się to automatycznie dzięki bibliotece Jackson w Spring;
    //Wymaga ona geterów wszystkich pól bezparanetrowego konstruktora i zgodnych nazw z jsonem
    private int id;
    private String name;

    public ItemDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ItemDto() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

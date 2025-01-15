package com.escapeRoom.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class ConnectionDTO {

    private int from;
    private int to;

    public ConnectionDTO(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public ConnectionDTO() {

    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    @JsonIgnore
    public List<Integer> getConnections() {
        return List.of(from,to);
    }







}

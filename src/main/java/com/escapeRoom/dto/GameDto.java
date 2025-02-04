package com.escapeRoom.dto;

import java.util.List;

public class GameDto {

    private int id;
    private RoomDto firstRoom;
    private int activeRoom;
    private List<ConnectionDTO> connections;
    private List<RoomDto> rooms;


    public GameDto() {
    }

    public GameDto(int id, RoomDto roomDTO, List<ConnectionDTO> connections, int activeRoom, List<RoomDto>roomsList ) {
        this.id = id;
        this.firstRoom =roomDTO;
        this.connections = connections;
        this.activeRoom=activeRoom;
        this.rooms=roomsList;
    }

    public int getId() {
        return id;
    }

    public RoomDto getFirstRoom() {
        return firstRoom;
    }

    public List<RoomDto> getRooms() {
        return rooms;
    }

    public List<ConnectionDTO> getConnections() {
        return connections;
    }

    public int getActiveRoom() {
        return activeRoom;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setFirstRoom(RoomDto firstRoom) {
        this.firstRoom = firstRoom;
    }

    public void setConnections(List<ConnectionDTO> connections) {
        this.connections = connections;
    }

    public void setActiveRoom(int activeRoom) {
        this.activeRoom = activeRoom;
    }


    @Override
    public String toString() {
        return "GameDto{" +
                "id=" + id +
                ", firstRoom=" + firstRoom +
                ", activeRoom=" + activeRoom +
                ", connections=" + connections +
                ", rooms=" + rooms +
                '}';
    }
}

package com.escapeRoom.dto;

public class ActionResultDto {

    private String text;

    public ActionResultDto(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ActionResultDto{" +
                "text='" + text + '\'' +
                '}';
    }
}

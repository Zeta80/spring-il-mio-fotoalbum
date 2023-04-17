package com.learning.fotoalbum.model;

public class CrudMessage {

    //ATTRIBUTES
    public enum CrudMessageType{
        SUCCESS, ERROR
    }

    private CrudMessageType type;
    private String text;

    //CONSTRUCTORS
    public CrudMessage() {}
    public CrudMessage(CrudMessageType type, String text) {
        this.type = type;
        this.text = text;
    }

    //GETTER & SETTER
    public CrudMessageType getType() {
        return type;
    }

    public void setType(CrudMessageType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    //METHODS
    @Override
    public String toString() {
        return "CrudMessage{" +
                "type=" + type +
                ", text='" + text + '\'' +
                '}';
    }
}


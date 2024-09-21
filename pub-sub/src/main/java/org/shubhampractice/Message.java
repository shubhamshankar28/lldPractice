package org.shubhampractice;

public class Message {
    private String message;
    private final String id;

    Message(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }
}

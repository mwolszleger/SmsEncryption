package com.example.michal.smsencryption;

/**
 * Created by Michal on 28.01.2017.
 */

public class Message {
    String text;
    String author;

    public Message(String text, String author) {
        this.text = text;
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }




}

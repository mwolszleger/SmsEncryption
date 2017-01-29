package com.example.michal.smsencryption;

import android.content.Context;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.ArrayMap;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by Michal on 28.01.2017.
 */

public final class MessagesContainer {
    private static MessageReceivedListener messageReceivedListener;
    public static ArrayList<Message> messages = new ArrayList<Message>();
    public static ArrayList<String> authors=new ArrayList<>();

    public static void add(String text,String author) {
        messages.add(new Message(text,author));
        authors.add(author);

        try {
            handleNewMessage();
        }
        catch(Exception e){}


    }
    public static void delete(int i,Context c)
    {
        messages.remove(i);
        authors.remove(i);
        SharedPreferencesHandling.delete(i,c);
        handleNewMessage();
    }
    public static Message  getMessage(int index)
    {
        return messages.get(index);

    }



    private static void handleNewMessage() {
        messageReceivedListener.onMessageReceived();
    }
    public static void setOnNewMessageReceivedListener(MessageReceivedListener listener) {
        messageReceivedListener=listener;
    }

}
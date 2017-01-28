package com.example.michal.smsencryption;

/**
 * Created by Michal on 28.01.2017.
 */

public interface MessageReceivedListener {

    void onMessageReceived(String text,String author);
}

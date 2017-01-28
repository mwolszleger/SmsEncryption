package com.example.michal.smsencryption;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.ArrayMap;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Michal on 28.01.2017.
 */

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        Bundle bundle = arg1.getExtras();
        SmsMessage[] msgs = null;
        String wiadomosc = "";
        Object[] pdus = (Object[]) bundle.get("pdus");
        msgs = new SmsMessage[pdus.length];

        for (int x = 0; x < msgs.length; x++) {
            msgs[x] = SmsMessage.createFromPdu((byte[]) pdus[x]);
            //wiadomosc = msgs[x].getOriginatingAddress();
            MessagesContainer.add(msgs[x].getMessageBody(),msgs[x].getOriginatingAddress());
            //Toast.makeText(arg0, wiadomosc, Toast.LENGTH_LONG).show();

        }
    }}


    /**
     * Created by Michal on 28.01.2017.
     */

package com.example.michal.smsencryption;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Created by Michal on 28.01.2017.
 */

public class SMSReceiver extends BroadcastReceiver {

    static boolean initialize=true;
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

            SharedPreferencesHandling.read(arg0);


            MessagesContainer.add(msgs[x].getMessageBody(),msgs[x].getOriginatingAddress());
            SharedPreferencesHandling.save(msgs[x].getMessageBody(),msgs[x].getOriginatingAddress(),arg0);
        }
    }

}


    /**
     * Created by Michal on 28.01.2017.
     */

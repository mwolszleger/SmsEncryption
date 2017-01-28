package com.example.michal.smsencryption;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.Set;

/**
 * Created by Michal on 28.01.2017.
 */

public class SharedPreferencesHandling {

private static boolean initialize=true;
public static void read(Context arg0)
{
    if(initialize) {
        //oast.makeText(arg0, "pierwszy raz", Toast.LENGTH_LONG).show();
        initialize = false;
        SharedPreferences sharedPref = arg0.getSharedPreferences("messages", 0);
        SharedPreferences sharedPref2 = arg0.getSharedPreferences("authors", 0);
        int length = sharedPref.getAll().size();
        int index = 0;
        int counter = 0;
        while (counter < length)
        {
            if(sharedPref.contains(Integer.toString(index)))
            {
                String message=sharedPref.getString(Integer.toString(index),"");
                String author=sharedPref2.getString(Integer.toString(index),"");
                MessagesContainer.add(message,author);
                counter++;

            }
            index++;



        }


    }

}
    public static void save(String message,String author,Context arg0)
    {

        String tag=getFreeTag(arg0);
        SharedPreferences sharedPref= arg0.getSharedPreferences("messages", 0);
        SharedPreferences.Editor mEditor = sharedPref.edit();
        mEditor.putString(tag, message).commit();
        SharedPreferences sharedPref2= arg0.getSharedPreferences("authors", 0);
        SharedPreferences.Editor mEditor2 = sharedPref2.edit();
        mEditor2.putString(tag, author).commit();

    }

    private static String getFreeTag(Context c)
    {
        int tag=0;
        SharedPreferences sharedPref= c.getSharedPreferences("messages", 0);

        Set<String> keys=sharedPref.getAll().keySet();
        for(String key : keys)
        {
            if(Integer.parseInt(key)>tag)
            {
                tag=Integer.parseInt(key);
            }

        }
        return Integer.toString(tag+1);

    }

}

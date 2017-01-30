package com.example.michal.smsencryption;

/**
 * Created by Michal on 28.01.2017.
 */

public class Encryption {

    private static final int beginChar=32;
    private static final int endChar=126;
    private static final int length=endChar-beginChar+1;


    public static  String EncryptMessage(String message,String password)
    {
        int len=password.length();
        String encrypted="";
        if(len==0)
        {
            return message;
        }
        for(int i=0;i<message.length();i++)
        {
            if(message.charAt(i)<beginChar||message.charAt(i)>endChar)
                encrypted+=message.charAt(i);
            else
                encrypted+=(char)((message.charAt(i)-beginChar+password.charAt(i%len))%length+beginChar);
        }
        return encrypted;
    }
    public static String DecryptMessage(String message,String password)
    {
        int len=password.length();
        String encrypted="";
        if(len==0)
        {
            return message;
        }
        for(int i=0;i<message.length();i++)
        {
            if(message.charAt(i)<beginChar||message.charAt(i)>endChar)
                encrypted+=message.charAt(i);
            else
            {
                char res=message.charAt(i);
                res-=beginChar;
                res+=(length-password.charAt(i%len)%length);
                res=(char)(res%length);
                res+=beginChar;
                encrypted+=res;
            }
        }
        return encrypted;
    }

}

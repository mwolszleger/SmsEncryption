package com.example.michal.smsencryption;

/**
 * Created by Michal on 28.01.2017.
 */

public class Encryption {

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
            if(message.charAt(i)<32||message.charAt(i)>127)
                encrypted+=message.charAt(i);
            else
                encrypted+=(char)((message.charAt(i)-32+password.charAt(i%len))%95+32);
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
            if(message.charAt(i)<32||message.charAt(i)>127)
                encrypted+=message.charAt(i);
            else
            {
                char res=message.charAt(i);
                res-=32;
                res+=(95-password.charAt(i%len)%95);
                res=(char)(res%95);
                res+=32;
                encrypted+=res;
            }
        }
        return encrypted;
    }
    private int mod(int a,int n)
    {
        int result = a%n;
        if(a<0)
            return result+n;
        else return result;
    }
}

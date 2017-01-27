package com.example.michal.smsencryption;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.pm.PackageManager;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    Button buttonSend;
    EditText textPhoneNo;
    EditText textSMS;
    EditText textPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("aaa",""+(-9%7));
        buttonSend = (Button) findViewById(R.id.buttonSend);
        textPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
        textSMS = (EditText) findViewById(R.id.editTextSMS);
        textPassword = (EditText) findViewById(R.id.editTextPassword);
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        0);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        buttonSend.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                String phoneNo = textPhoneNo.getText().toString();
                String sms = EncryptMessage(textSMS.getText().toString(),textPassword.getText().toString());

                for(int i=0;i<sms.length();i++)
                {
                    Log.e("oryginal",textSMS.getText().toString().charAt(i)+"");
                    Log.e("oryginalNumber",(int)(textSMS.getText().toString().charAt(i))+"");
                    Log.e("encrypted",sms.charAt(i)+"");
                    Log.e("encryptedNumner",(int)(sms.charAt(i))+"");

                }
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                    Toast.makeText(getApplicationContext(), "SMS Sent!",
                            Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again later!"+e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });
    }
    public String EncryptMessage(String message,String password)
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
    public String DecryptMessage(String message,String password)
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

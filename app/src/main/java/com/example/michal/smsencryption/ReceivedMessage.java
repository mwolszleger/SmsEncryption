package com.example.michal.smsencryption;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ReceivedMessage extends AppCompatActivity {
private String originalText;
    EditText password;
    Button buttonDecrypt;
    TextView messagesDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received_message);
        originalText = getIntent().getStringExtra("TEXT");
        password = (EditText) findViewById(R.id.editText);
        buttonDecrypt = (Button) findViewById(R.id.button2);
        messagesDisplay = (TextView) findViewById(R.id.textView);
        messagesDisplay.setText(originalText);


    }
    public void decrypt(View v) {
        messagesDisplay.setText(Encryption.DecryptMessage(originalText,password.getText().toString()));
    }
}

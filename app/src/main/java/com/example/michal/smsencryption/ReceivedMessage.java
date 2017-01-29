package com.example.michal.smsencryption;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ReceivedMessage extends AppCompatActivity {
private String originalText;
    private String author;
    EditText password;
    Button buttonDecrypt;
    TextView messagesDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received_message);
        originalText = getIntent().getStringExtra("TEXT");
        author = getIntent().getStringExtra("AUTHOR");
        password = (EditText) findViewById(R.id.editText);
        buttonDecrypt = (Button) findViewById(R.id.button2);
        messagesDisplay = (TextView) findViewById(R.id.textView);
        messagesDisplay.setText(originalText);


    }
    public void decrypt(View v) {
        messagesDisplay.setText(Encryption.DecryptMessage(originalText,password.getText().toString()));
    }
    public void answer(View v) {
        Intent intent_newMessage = new Intent(this, NewMessageActivity.class);
        intent_newMessage.putExtra("AUTHOR", author);
        startActivity(intent_newMessage);
    }
    public void delete(View v) {
       MessagesContainer.delete(getIntent().getIntExtra("INDEX",0),v.getContext());
        finish();
    }
}

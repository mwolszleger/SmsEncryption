package com.example.michal.smsencryption;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MessagesActivity extends AppCompatActivity {

    private ListView listView;

    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        setPermissions();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MessagesContainer.authors);
        listView = (ListView)findViewById(R.id.messagesList);
        listView.setAdapter(adapter);
        SharedPreferencesHandling.read(this);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //view.setBackgroundColor(Color.TRANSPARENT);

                Intent conversation = new Intent(MessagesActivity.this,ReceivedMessage.class);
                conversation.putExtra("TEXT", MessagesContainer.getMessage(i).getText());
                conversation.putExtra("AUTHOR", MessagesContainer.getMessage(i).getAuthor());
                conversation.putExtra("INDEX", i);
                startActivity(conversation);
            }
        });
        MessagesContainer.setOnNewMessageReceivedListener(new MessageReceivedListener() {
            @Override
            public void onMessageReceived() {
                MessagesActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });



    }
    public void newMessage(View view) {
        Intent intent_newMessage = new Intent(this, NewMessageActivity.class);
        startActivity(intent_newMessage);
    }
    private void setPermissions()
    {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECEIVE_SMS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECEIVE_SMS},
                        0);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

        }

    }
}

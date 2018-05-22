package com.example.androidlayout.androidlayout;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class IndexActivity extends AppCompatActivity {
    private IntentFilter intentFilter;

    private NetworkChangeReceiver networkChangeReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);


        Button button = findViewById(R.id.index_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexActivity.this, LinearLayoutCalcActivity.class);
                startActivity(intent);
            }
        });

        Button button_DaTi = findViewById(R.id.index_button2);
        button_DaTi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexActivity.this, DaTiActivity.class);
                startActivity(intent);
            }
        });


        Button index_editBtn = findViewById(R.id.index_editBtn);
        index_editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexActivity.this, EditViewActivity.class);
                startActivity(intent);
            }
        });


        //注册网络变化广播监听-显示注册监听
//        intentFilter = new IntentFilter();
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        //intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
//        //intentFilter.addAction("android.net.wifi.STATE_CHANGE");
//        networkChangeReceiver = new NetworkChangeReceiver();
//        registerReceiver(networkChangeReceiver,intentFilter);



        Button sendBroadcastBtn = findViewById(R.id.index_sendBroadcastBtn);
        sendBroadcastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("IndexActivity", "onClick: sendBroadcastBtn");
                Intent intent = new Intent("com.example.androidlayout.androidlayout.MY_BROADCAST");
                sendBroadcast(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(networkChangeReceiver);
    }
}

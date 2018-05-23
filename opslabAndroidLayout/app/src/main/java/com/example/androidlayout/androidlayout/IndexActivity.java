package com.example.androidlayout.androidlayout;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class IndexActivity extends AppCompatActivity {
    private IntentFilter intentFilter;

    private NetworkChangeReceiver networkChangeReceiver;

    private LocalBroadcastManager localBroadcastManager;

    private LocalBroadcastReceiver localBroadcastReceiver;

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

        //使用本地的广播系统
        //本地广播协议只能使用动态注册不能使用静态注册
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.androidlayout.androidlayout.LOCAL_BROADCAST");
        localBroadcastReceiver = new LocalBroadcastReceiver();
        localBroadcastManager.registerReceiver(localBroadcastReceiver,intentFilter);


        Button localBroadcastBtn = findViewById(R.id.index_localsendBroadcastBtn);
        localBroadcastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("IndexActivity", "onClick: LOCAL_BROADCAST");
                Intent intent = new Intent("com.example.androidlayout.androidlayout.LOCAL_BROADCAST");
                localBroadcastManager.sendBroadcast(intent);
            }
        });



        //演示使用SharedPrefereneces存储和读取数据
        Button sharedPreftBtn = findViewById(R.id.index_sharedpreferences);
        sharedPreftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexActivity.this, SharedPreferencesActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(networkChangeReceiver);

        if(localBroadcastManager != null){
            localBroadcastManager.unregisterReceiver(localBroadcastReceiver);
        }
    }
}

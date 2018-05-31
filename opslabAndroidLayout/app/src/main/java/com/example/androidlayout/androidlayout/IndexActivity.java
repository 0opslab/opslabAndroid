package com.example.androidlayout.androidlayout;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
        localBroadcastManager.registerReceiver(localBroadcastReceiver, intentFilter);


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


        //演示动态申请权限
        Button callPhoneBtn = findViewById(R.id.index_callPhone);
        callPhoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //传统的方式
//                try{
//                    Intent intent = new Intent(Intent.ACTION_CALL);
//                    intent.setData(Uri.parse("tel:10086"));
//                    startActivity(intent);
//                }catch (SecurityException e){
//                    e.printStackTrace();
//                }
                //动态申请权限
                if (ContextCompat.checkSelfPermission(IndexActivity.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(IndexActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    callPhone("tel:10086");
                }
            }
        });



        //读取通讯录
        //演示使用SharedPrefereneces存储和读取数据
        Button readContactsBtn = findViewById(R.id.index_ReadContactsBtn);
        readContactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexActivity.this, ReadContactsActivity.class);
                startActivity(intent);
            }
        });

        Button photocaptureBtn = findViewById(R.id.index_photocapture);
        photocaptureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexActivity.this, PhotographActivity.class);
                startActivity(intent);
            }
        });

        Button photoselectBtn = findViewById(R.id.index_photoselect);
        photoselectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexActivity.this, ImageSelectedActivity.class);
                startActivity(intent);
            }
        });

        Button webviewBtn = findViewById(R.id.index_webview);
        webviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });


        Button httBtn = findViewById(R.id.index_httpbtn);
        httBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexActivity.this, HTTPActivity.class);
                startActivity(intent);
            }
        });

        Button okhttpBtn = findViewById(R.id.index_okhttpbtn);
        okhttpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexActivity.this, OKHttpActivity.class);
                startActivity(intent);
            }
        });

        Button messageHandlerBtn = findViewById(R.id.index_messageHandler);
        messageHandlerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexActivity.this, MessageHandlerActivity.class);
                startActivity(intent);
            }
        });

        Button asyncTaskBtn = findViewById(R.id.index_AsyncTask);
        asyncTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexActivity.this, AsyncTaskImageActivity.class);
                startActivity(intent);
            }
        });

        Button serviceBtn = findViewById(R.id.index_service);
        serviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexActivity.this, ServiceActivity.class);
                startActivity(intent);
            }
        });




        Button includeLayoutBtn = findViewById(R.id.index_includeLayout);
        includeLayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexActivity.this, IncludeLayoutActivity.class);
                startActivity(intent);
            }
        });

        Button listViewBtn = findViewById(R.id.index_listView);
        listViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });

        Button webView2Btn = findViewById(R.id.index_webview2);
        webView2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexActivity.this, WebView2Activity.class);
                startActivity(intent);
            }
        });


    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPhone("tel:10086");
                } else {
                    Toast.makeText(this, "YOU DENIED THE PERMISSION", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(networkChangeReceiver);

        if (localBroadcastManager != null) {
            localBroadcastManager.unregisterReceiver(localBroadcastReceiver);
        }
    }

    //拨号
    private void callPhone(String mobile) {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse(mobile));
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }




}

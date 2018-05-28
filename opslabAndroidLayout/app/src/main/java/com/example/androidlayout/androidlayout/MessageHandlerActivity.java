package com.example.androidlayout.androidlayout;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MessageHandlerActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int UPDATE_TEXT = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    //异步更新UI
                    TextView textView = findViewById(R.id.messagehander_textview);
                    textView.setText("收到信息");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_handler);

        Button okhttpBtn = findViewById(R.id.messagehander_send);
        okhttpBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.messagehander_send) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //开启另外一个线程执行耗时操作，然后异步通知更新UI
                    Message message = new Message();
                    message.what = UPDATE_TEXT;
                    //将Message对象异步发送
                    handler.sendMessage(message);
                }
            }).start();
        }
    }
}

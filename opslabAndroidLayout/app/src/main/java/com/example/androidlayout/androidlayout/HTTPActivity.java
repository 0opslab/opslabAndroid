package com.example.androidlayout.androidlayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);


        Button httBtn = findViewById(R.id.http_httpget);
        httBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpURLConnection connection = null;
                        BufferedReader read = null;

                        try{
                            URL url = new URL("https://0opslab.com");
                            connection = (HttpURLConnection)url.openConnection();
                            connection.setRequestMethod("GET");
                            connection.setConnectTimeout(8000);
                            InputStream in = connection.getInputStream();
                            read = new BufferedReader(new InputStreamReader(in));
                            StringBuilder response = new StringBuilder();
                            String line;
                            while((line = read.readLine()) != null){
                                response.append(line);
                            }
                            setResult(response.toString());
                        }catch (Exception e){
                            e.printStackTrace();
                        }finally {
                            if(connection != null){
                                connection.disconnect();
                            }
                        }
                    }
                }).start();
            }
        });
    }

    private void setResult(String str){
        TextView textView = findViewById(R.id.http_textview);
        textView.setText(str);
    }
}

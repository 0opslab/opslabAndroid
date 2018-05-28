package com.example.androidlayout.androidlayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        Button startButton = findViewById(R.id.service_start);
        Button stopButton = findViewById(R.id.service_stop);
        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(ServiceActivity.this,
                "=============>onClick: ViewId"+view.getId(),Toast.LENGTH_SHORT).show();
        switch (view.getId()) {
            case R.id.service_start:
                Intent startintent = new Intent(this,MyService.class);
                startService(startintent);
                break;
            case R.id.service_stop:
                Intent endintent = new Intent(this,MyService.class);
                stopService(endintent);
                break;
            default:
                break;
        }

    }
}

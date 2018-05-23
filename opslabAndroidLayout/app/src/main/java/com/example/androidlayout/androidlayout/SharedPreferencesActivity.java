package com.example.androidlayout.androidlayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SharedPreferencesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);



        Button sharedPreftSaveBtn = findViewById(R.id.sharedpreferences_save);
        sharedPreftSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText text = findViewById(R.id.sharedpreferences_text);
                String content = text.getText().toString();

                SharedPreferences.Editor sharepreferencesdata =
                        getSharedPreferences("sharepreferencesdata", MODE_PRIVATE).edit();

                sharepreferencesdata.putString("data-key",content);
                sharepreferencesdata.apply();
                Toast toast = Toast.makeText(SharedPreferencesActivity.this,
                        "Save Success",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        Button sharedPrefReadBtn = findViewById(R.id.sharedpreferences_read);
        sharedPrefReadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharepreferences =
                        getSharedPreferences("sharepreferencesdata", MODE_PRIVATE);

                String content = sharepreferences.getString("data-key", "");
                Toast toast = Toast.makeText(SharedPreferencesActivity.this,
                        "GetContent:"+content,Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }
}

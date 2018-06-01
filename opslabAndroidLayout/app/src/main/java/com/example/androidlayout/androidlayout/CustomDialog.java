package com.example.androidlayout.androidlayout;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidlayout.androidlayout.R;

/**
 * 自定义对话框
 * Created by monsoon on 2018/6/1 0001.
 */

public class CustomDialog extends Dialog {
    private  Context context;

    public CustomDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.custom_dialog);
        setTitle("选择播放列表");

        TextView text = findViewById(R.id.text);
        text.setText("Hello, this is a custom dialog!");


        ImageView image = (ImageView)findViewById(R.id.image);
        image.setImageResource(R.drawable.music_ico);

        Button buttonYes = (Button) findViewById(R.id.button_yes);
        buttonYes.setHeight(5);
        buttonYes.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v) {
                Toast.makeText(context,"单击YES",Toast.LENGTH_SHORT).show();
                dismiss();

            }
        });
        Button buttonNo = (Button) findViewById(R.id.button_no);
        buttonNo.setSingleLine(true);
        buttonNo.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v) {
                Toast.makeText(context,"单击NON",Toast.LENGTH_SHORT).show();
                dismiss();

            }
        });
    }

    //called when this dialog is dismissed
    protected void onStop() {
        Log.d("TAG","+++++++++++++++++++++++++++");
    }
}

package com.example.androidlayout.androidlayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;



/**
 * Created by piu on 2018/5/29 0029.
 */

public class MyTitleLayout extends LinearLayout {
    public MyTitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.activity_include_layout,this);
    }
}

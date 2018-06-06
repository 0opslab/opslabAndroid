package com.example.androidlayout.androidlayout.webview;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by monsoon on 2018/6/6 0006.
 */

public class AndroidJsMethod extends Object {
    private Context context;

    public AndroidJsMethod(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    private void hello(String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    private  String hellos(String msg){
        return "return android string:"+msg;
    }
}

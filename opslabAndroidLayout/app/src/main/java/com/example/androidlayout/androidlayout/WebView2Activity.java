package com.example.androidlayout.androidlayout;

import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WebView2Activity extends AppCompatActivity implements View.OnClickListener {

    private Button BACKBTN;
    private Button EDITBTN;
    private TextView TITLE;
    private WebView WEBVIEW;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view2);

        BACKBTN = findViewById(R.id.webview2_back);
        EDITBTN = findViewById(R.id.webview2_edit);
        TITLE = findViewById(R.id.webview2_title);
        WEBVIEW = findViewById(R.id.webview2_webview);


        //声明WebSettings子类
        WebSettings webSettings = WEBVIEW.getSettings();

//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

//支持插件
//        webSettings.setPluginsEnabled(true);

//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

//缩放操作
        webSettings.setSupportZoom(false); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(true); //隐藏原生的缩放控件

//其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片

        //优先使用缓存:
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //缓存模式如下：
        //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
        //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。

        WEBVIEW.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //设定加载开始的操作
                Toast.makeText(WebView2Activity.this, "loading...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //设定加载结束的操作
                //设定加载开始的操作
                Toast.makeText(WebView2Activity.this, "Finished", Toast.LENGTH_SHORT).show();
            }
        });


        WEBVIEW.setWebChromeClient(new WebChromeClient(){
            //不支持js的alert弹窗，需要自己监听然后通过dialog弹窗
            @Override
            public boolean onJsAlert(WebView webView, String url, String message, JsResult result) {
                AlertDialog.Builder localBuilder = new AlertDialog.Builder(webView.getContext());
                localBuilder.setMessage(message).setPositiveButton("确定",null);
                localBuilder.setCancelable(false);
                localBuilder.create().show();

                //注意:
                //必须要这一句代码:result.confirm()表示:
                //处理结果为确定状态同时唤醒WebCore线程
                //否则不能继续点击按钮
                result.confirm();
                return true;
            }

            //获取网页标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                TITLE.setText(title);
            }


        });


        WEBVIEW.loadUrl("https://blog.0opslab.com");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.webview2_back:
                Toast.makeText(WebView2Activity.this, "back", Toast.LENGTH_SHORT).show();
                WEBVIEW.goBack();   //返回上一页面
                break;
            case R.id.webview2_edit:
                Toast.makeText(WebView2Activity.this, "edit", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}

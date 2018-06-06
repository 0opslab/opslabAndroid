package com.example.androidlayout.androidlayout;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.example.androidlayout.androidlayout.webview.AndroidJsMethod;

public class WebViewJSActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_js);

        webView = findViewById(R.id.webviewjs_webview);
        //  加载、并显示HTML代码
        StringBuilder sb = new StringBuilder("<!DOCTYPE html><html><head>" +
                "<meta charset=\"utf-8\"><script>function callJS(){alert(\"Android调用了JS的callJS方法\");}</script></head>"+
                "<body><h1 id=\"text\">html页面</h1>"+
                "<button onclick=\"AppNav.hello('111')\">调用原生js方法</button>" +
                "<button onclick=\"alert(AppNav.hellos('111'));\">调用原生js方法返回</button>" +
                "</body></html>");
        webView.loadDataWithBaseURL(null, sb.toString(), "text/html", "utf-8", null);

        WebSettings webSettings = webView.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);


        // 通过addJavascriptInterface()将Java对象映射到JS对象
        //参数1：Javascript对象名
        //参数2：Java对象名
        webView.addJavascriptInterface(new AndroidJsMethod(WebViewJSActivity.this), "AppNav");//AndroidtoJS类对象映射到js的test对象


        // 由于设置了弹窗检验调用结果,所以需要支持js对话框
        // webview只是载体，内容的渲染需要使用webviewChromClient类去实现
        // 通过设置WebChromeClient对象处理JavaScript的对话框
        //设置响应js 的Alert()函数
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(WebViewJSActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }

        });

        Button button = findViewById(R.id.webviewjs_calljs);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Android版本变量
                final int version = Build.VERSION.SDK_INT;
                // 因为该方法在 Android 4.4 版本才可使用，所以使用时需进行版本判断
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    webView.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {
                            //此处为 js 返回的结果
                        }
                    });
                } else {
                    webView.loadUrl("javascript:callJS()");
                }
            }
        });



    }


}

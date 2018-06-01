package com.example.androidlayout.androidlayout;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import java.util.Timer;

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


        Button modalDialogBtn = findViewById(R.id.index_modalDialog);
        modalDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad=new AlertDialog.Builder(IndexActivity.this);
    		/*
    		setTitle()：给对话框设置title.
    		setIcon():给对话框设置图标。
    		setMessage():设置对话框的提示信息
    		setItems()：设置对话框要显示的一个list,一般用于要显示几个命令时
    		setSingleChoiceItems():设置对话框显示一个单选的List
    		setMultiChoiceItems():用来设置对话框显示一系列的复选框。
    		setPositiveButton():给对话框添加”Yes”按钮。
    		setNegativeButton():给对话框添加”No”按钮。
    		show():显示对话框，一般放最后
    		*/
                ad.setIcon(R.drawable.music_ico);
                ad.setTitle("选项");//设置对话框标题
                ad.setMessage("操作");//设置对话框内容
                ad.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                });
                ad.setNegativeButton("官方网站",new DialogInterface.OnClickListener() {
                    //显示官方网站按钮，点击打开浏览器，转向www.pocketdigi.com
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Uri uri=Uri.parse("https://blog.0opslab.com");
                        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                    }
                });

                //ad.setItems(new String[]{"播放","从列表中移除"},new itemListonClick());
                ad.show();//显示对话框
            }
        });


        Button customDialogBtn = findViewById(R.id.index_customDialog);
        customDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int m_count = 0;
                //声明进度条对话框
                final ProgressDialog m_pDialog;
                //创建ProgressDialog对象
                m_pDialog = new ProgressDialog(IndexActivity.this);

                // 设置进度条风格，风格为圆形，旋转的
                m_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

                m_pDialog.getWindow().setGravity(Gravity.CENTER);

                // 设置ProgressDialog 标题
                //m_pDialog.setTitle("提示");

                // 设置ProgressDialog 提示信息
                //m_pDialog.setMessage("正在处理中...");

                // 设置ProgressDialog 标题图标
                //  m_pDialog.setIcon(R.drawable.img1);

                // 设置ProgressDialog 的进度条是否不明确
                m_pDialog.setIndeterminate(false);

                // 设置ProgressDialog 是否可以按退回按键取消
                m_pDialog.setCancelable(true);

                // 设置ProgressDialog 的一个Button
            /*
            m_pDialog.setButton("确定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int i)
                {
                    //点击“确定按钮”取消对话框

                }
            });
			*/
                // 让ProgressDialog显示
                m_pDialog.show();
                Timer timer = new Timer();
                class MyTask extends java.util.TimerTask{
                    int num=0;
                    public void run() {
                        num++;
                        System.out.println("  己花费   "+num+" S");
                        if(num>=4)
                        {
                            m_pDialog.cancel();
                        }
                    }
                }
                timer.schedule(new MyTask(), 1, 1000); //在1毫秒后执行此任务,每次间隔1秒
            }
        });



        Button custom2DialogBtn = findViewById(R.id.index_custom2Dialog);
        custom2DialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=IndexActivity.this;
                CustomDialog cd = new CustomDialog(context);
                cd.show();
            }
        });


        Button selectDialogBtn = findViewById(R.id.index_selectDialog);
        selectDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View views = getLayoutInflater().inflate(R.layout.custom_select_dialog, null);
                Dialog dialog = new Dialog(IndexActivity.this, R.style.transparentFrameWindowStyle);
                dialog.setContentView(views, new LayoutParams(LayoutParams.FILL_PARENT,
                        LayoutParams.WRAP_CONTENT));

                Window window = dialog.getWindow();
                // 设置显示动画
                window.setWindowAnimations(R.style.main_menu_animstyle);
                WindowManager.LayoutParams wl = window.getAttributes();
                wl.x = 0;
                wl.y = getWindowManager().getDefaultDisplay().getHeight();
                // 以下这两句是为了保证按钮可以水平满屏
                wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
                wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

                // 设置显示位置
                dialog.onWindowAttributesChanged(wl);
                // 设置点击外围解散
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
            }
        });



        Button imglbBtn = findViewById(R.id.index_imglb);
        imglbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexActivity.this, TuiJianActivity.class);
                startActivity(intent);
            }
        });


        Button paizhaoBtn = findViewById(R.id.index_paizhao);
        paizhaoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexActivity.this, PaiZhaoActivity.class);
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

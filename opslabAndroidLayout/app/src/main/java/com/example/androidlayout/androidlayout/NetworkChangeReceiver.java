package com.example.androidlayout.androidlayout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * 实现网络变化广播监听器
 * Created by piu on 2018/5/21 0021.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("NetworkChangeReceiver", "onReceive: Network changes");
        Toast.makeText(context,"Network changes",Toast.LENGTH_SHORT).show();

        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isAvailable()){
            Toast.makeText(context,"Network is Available",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Network is UnAvailable",Toast.LENGTH_SHORT).show();
        }
    }
}

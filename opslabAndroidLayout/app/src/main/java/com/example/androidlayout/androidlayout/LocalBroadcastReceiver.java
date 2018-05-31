package com.example.androidlayout.androidlayout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by piu on 2018/5/23 0023.
 */

public class LocalBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"received in LocalBroadcastReceiver" +
                "在本地广播接收器中接受到消息",Toast.LENGTH_LONG).show();
    }
}

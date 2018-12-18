package com.example.app.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author lzl
 * @ describe
 * @ time 2018/12/18 16:09
 */
public class DynamicReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("TAG","DynamicReceiver");
    }
}

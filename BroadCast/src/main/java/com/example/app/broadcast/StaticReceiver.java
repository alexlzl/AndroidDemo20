package com.example.app.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 *
 */
/**
 * @ describe 
 * 
 * @author lzl
 *
 * @ time 2018/12/18 17:10
 * 
 * @ param
 * 
 * @ return
 */
public class StaticReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,intent.getStringExtra("msg"),Toast.LENGTH_SHORT).show();
    }
}

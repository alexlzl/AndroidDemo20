package com.example.demo2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * @ describe
 *
 * @author lzl
 *
 * @ time 2018/12/18 14:28
 *
 *
 */
class CustomReceiver:BroadcastReceiver(){
    override fun onReceive(p0: Context?, p1: Intent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Toast.makeText(p0,p1?.action,Toast.LENGTH_LONG).show()
    }

}
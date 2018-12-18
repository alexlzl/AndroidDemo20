package com.example.demo2

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//      registerReceiver(CustomReceiver(), IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))

    }

    fun test1(view : View){
        if(view is Button){
            view.text=Build.SERIAL
            sendBroadcast(Intent("com.example.normal.receiver"))
        }
    }
}

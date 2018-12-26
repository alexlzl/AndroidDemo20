package com.example.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var channelID="1"
    var channelName="channel_name"
//    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        show_notification.setOnClickListener{
            if(Build.VERSION.SDK_INT>=26){

                Toast.makeText(this,"test",Toast.LENGTH_LONG).show()
                val notificationChannel= NotificationChannel(channelID,channelName,NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor= Color.RED
                notificationChannel.enableVibration(true)
                notificationChannel.vibrationPattern=longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
                val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE)
                (notificationManager  as NotificationManager).createNotificationChannel(notificationChannel)
                val notificationBuild=Notification.Builder(this)
                notificationBuild.setContentText("notificationnotificationnotificationnotificationnotificationnotificationnotificationnotificationnotificationnotificationnotificationnotification")
                notificationBuild.setContentTitle("title")
                notificationBuild.setChannelId(channelID)
                notificationBuild.setSmallIcon(R.mipmap.ic_launcher)
                val notification=notificationBuild.build()
                notificationManager .notify(1,notification)

            }
        }
    }
}

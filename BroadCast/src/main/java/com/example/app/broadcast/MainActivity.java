package com.example.app.broadcast;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.app.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * https://blog.csdn.net/qq_17250009/article/details/53896168
 * <p>
 * 2.原因：Android8在静态广播的使用上做了一些限制具体可查看：https://developer.android.google.cn/about/versions/oreo/android-8.0.html
 * <p>
 * 3. 解决办法：
 * <p>
 * （1）使用动态广播代替静态广播，具体可参考博客1。
 * <p>
 * （2）保留原来的静态广播，但是加入Component参数，具体可参考博客2。
 * ---------------------
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
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerReceiver(new DynamicReceiver(), new IntentFilter("com.test"));
        registerReceiver(new DynamicReceiver1(), new IntentFilter("com.test"));

    }

    public void onClick(View view) {
        //Context.getPackageName()方法返回Application ID
        ((Button) view).setText(getPackageName());
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent("com.example.test");
                intent.setComponent(new ComponentName("com.example.app.broadcast", "com.example.app.broadcast.StaticReceiver"));
                intent.putExtra("msg", "hello kugou");
                Log.i("sended", "我发送了");
                sendBroadcast(intent);
            }
        },3000);
//        Intent intent = new Intent("com.example.test");
//        intent.setComponent(new ComponentName("com.example.app.broadcast", "com.example.app.broadcast.StaticReceiver"));
//        intent.putExtra("msg", "hello kugou");
//        Log.i("sended", "我发送了");
//        sendBroadcast(intent);
    }

    public void onClick1(View view) {
        sendBroadcast(new Intent("com.test"));
    }
}

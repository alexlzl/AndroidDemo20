package com.carmelo.androidkeeplive;

import android.content.Intent;
import android.util.Log;

import com.carmelo.library.KeepliveService;

/**
 * Created by carmelo on 2018/3/17.
 */

public class DemoService extends KeepliveService{
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //do something
        int i = super.onStartCommand(intent, flags, startId);
        Log.e("TAG","DemoService PID = " + android.os.Process.myPid());
        Person person=new Person();
        return i;
    }
}

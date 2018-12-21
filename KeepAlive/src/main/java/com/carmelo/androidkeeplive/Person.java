package com.carmelo.androidkeeplive;

import android.util.Log;

/**
 * @author lzl
 * @ describe
 * @ time 2018/12/21 16:15
 */
public class Person {
    public Person(){
        Log.e("TAG","Person PID = " + android.os.Process.myPid());
    }
}

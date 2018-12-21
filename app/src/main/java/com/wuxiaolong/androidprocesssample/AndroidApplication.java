package com.wuxiaolong.androidprocesssample;

import android.app.Application;
import android.util.Log;


/**
 * Created by WuXiaolong on 2018/2/6.
 * 个人博客：http：//wuxiaolong.me
 */

public class AndroidApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String processName = AndroidUtil.getProcessName();
        if (getPackageName().equals(processName)) {
            //初始化操作
            Log.d("wxl", "AndroidApplication onCreate 2222=" + processName);
        }
        Log.d("wxl", "AndroidApplication onCreate=" + processName);
    }


}

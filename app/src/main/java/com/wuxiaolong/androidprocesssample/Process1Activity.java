package com.wuxiaolong.androidprocesssample;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class Process1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process1);
        Log.d("wxl", "userId=" + SingletonUtil.getInstance().getUserId());
        Object object = AndroidUtil.readObjectFromSDCard(SingletonUtil.ROOT_FILE_DIR, SingletonUtil.USER_STATE_FILE_NAME_DIR);
        if (object != null) {
            SingletonUtil singletonUtil = (SingletonUtil) object;
            Log.d("wxl", "userId=" + singletonUtil.getUserId());
        }
    }
}

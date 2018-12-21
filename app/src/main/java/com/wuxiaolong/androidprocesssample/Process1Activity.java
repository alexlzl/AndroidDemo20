package com.wuxiaolong.androidprocesssample;

import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Process1Activity extends AppCompatActivity {
    private TextView mTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process1);
        mTv=findViewById(R.id.test1);
        mTv.setText("process==id"+Process.myPid());
        Log.d("wxl", "userId=" + SingletonUtil.getInstance().getUserId());
        Object object = AndroidUtil.readObjectFromSDCard(SingletonUtil.ROOT_FILE_DIR, SingletonUtil.USER_STATE_FILE_NAME_DIR);
        if (object != null) {
            SingletonUtil singletonUtil = (SingletonUtil) object;
            Log.d("wxl", "userId=" + singletonUtil.getUserId());
        }
    }
}

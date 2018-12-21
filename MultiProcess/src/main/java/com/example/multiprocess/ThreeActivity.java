package com.example.multiprocess;

import android.app.Activity;
import android.os.Bundle;
import android.os.Process;
import android.widget.TextView;

/**
 * @author lzl
 * @ describe
 * @ time 2018/12/21 18:27
 */
public class ThreeActivity extends Activity {
    private TextView mTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three_layout);
        mTv=findViewById(R.id.test3);
        mTv.setText("ThreeActivity===processid==="+Process.myPid());
    }
}

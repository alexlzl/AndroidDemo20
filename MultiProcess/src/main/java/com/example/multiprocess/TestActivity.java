package com.example.multiprocess;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author lzl
 * @ describe
 * @ time 2018/12/21 13:47
 */
public class TestActivity extends Activity {
    private TextView mTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        mTv=findViewById(R.id.test2);
        mTv.setText("TestActivity===processid==="+Process.myPid()+getProcessName());
    }

    public void test2(View view){
        startActivity(new Intent(this,ThreeActivity.class));
    }

    public static String getProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

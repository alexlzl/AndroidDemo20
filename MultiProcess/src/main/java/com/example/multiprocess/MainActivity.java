package com.example.multiprocess;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
       private TextView mTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv=findViewById(R.id.test1);
        mTv.setText("MainActivity==processid==="+Process.myPid());
    }

    public void  test1(View view){
        startActivity(new Intent(this,TestActivity.class));
    }
}

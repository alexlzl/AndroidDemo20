package com.example.changeappicon1;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    String action ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test1(View view) {
        action = "comp2";
        pmTest();
    }

    public void test2(View view) {
        action = "comp3";
        pmTest();
    }

    private PackageManager mPackageManager;
    //默认组件
    private ComponentName componentNameDefault;
    private ComponentName componentName2;
    private ComponentName componentName3;

    /**
     * 设置第icon2图标生效
     */
    private void enableComponentName2() {
        disableComponent(componentNameDefault);
        disableComponent(componentName3);
        enableComponent(componentName2);
    }

    /**
     * 设置第icon3图标生效
     */
    private void enableComponentName3() {
        disableComponent(componentNameDefault);
        disableComponent(componentName2);
        enableComponent(componentName3);
    }

    /**
     * 启动组件
     *
     * @param componentName 组件名
     */
    private void enableComponent(ComponentName componentName) {
        //此方法用以启用和禁用组件，会覆盖Androidmanifest文件下定义的属性
        mPackageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    /**
     * 禁用组件
     *
     * @param componentName 组件名
     */
    private void disableComponent(ComponentName componentName) {
        mPackageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    //最后调用
    public void pmTest() {
        //获取到包管理类实例
        mPackageManager = getPackageManager();
        //得到此activity的全限定名
        componentNameDefault = getComponentName();
        //根据全限定名创建一个组件，即activity-alias 节点下的name：HomeActivity2 对应的组件
        componentName2 = new ComponentName(getBaseContext(), "com.example.changeappicon1.HomeActivity2");
        componentName3 = new ComponentName(getBaseContext(), "com.example.changeappicon1.HomeActivity3");
//        String action = getActionFromServer();//从后台获取到应该使用那一个组件，或者根据时间来判断

        if ("comp2".equals(action)) {
            enableComponentName2();
        } else if ("comp3".equals(action)) {
            enableComponentName3();
        }
        //如果没有，则显示默认图标
    }

}

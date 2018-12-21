package com.wuxiaolong.androidprocesssample;

import android.os.Environment;

import java.io.File;
import java.io.Serializable;

/**
 * Created by WuXiaolong on 2018/2/6.
 * 个人博客：http：//wuxiaolong.me
 */

public class SingletonUtil implements Serializable{
    public static String ROOT_FILE_DIR = Environment.getExternalStorageDirectory() + File.separator + "User" + File.separator;
    public static String USER_STATE_FILE_NAME_DIR = "UserState";
    private static SingletonUtil singletonUtil;
    private String userId = "0";

    public static SingletonUtil getInstance() {
        if (singletonUtil == null) {
            singletonUtil = new SingletonUtil();
        }
        return singletonUtil;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

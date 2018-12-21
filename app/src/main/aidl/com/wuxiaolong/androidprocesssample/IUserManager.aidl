// IUserManager.aidl
package com.wuxiaolong.androidprocesssample;

// Declare any non-default types here with import statements
//手动导入
import com.wuxiaolong.androidprocesssample.UserBean;

interface IUserManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    //基本数据类型：int，long，boolean，float，double，String
    void hello(String aString);

    //非基本数据类型，传递对象
    void getUser(in UserBean userBean);//in 客户端->服务端


}

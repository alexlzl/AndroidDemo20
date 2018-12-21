package com.wuxiaolong.androidprocesssample;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by WuXiaolong on 2018/2/7.
 * 个人博客：http：//wuxiaolong.me
 */

public class UserBean implements Parcelable {
    private int userId;
    private String userName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserBean() {

    }

    private UserBean(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
    }

    /**
     * @return 0 或 1 ，1 含有文件描述符
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 系列化
     *
     * @param dest  当前对象
     * @param flags 0 或 1，1 代表当前对象需要作为返回值，不能立即释放资源
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(userName);
    }

    /**
     * 反序列化
     */
    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel in) {
            return new UserBean(in);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };

}


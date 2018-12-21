package com.wuxiaolong.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Created by WuXiaolong on 2018/2/7.
 * 个人博客：http：//wuxiaolong.me
 */

public class AIDLService extends Service {
    private Binder binder = new IUserManager.Stub() {
        @Override
        public void hello(String aString) throws RemoteException {
            Log.d("wxl", aString + " from AIDL Service");
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}

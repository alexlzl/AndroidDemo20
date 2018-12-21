package com.wuxiaolong.androidprocesssample;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import static com.wuxiaolong.androidprocesssample.MainActivity.MSG_FROM_SERVICE;

/**
 * Created by WuXiaolong on 2018/2/6.
 * 个人博客：http：//wuxiaolong.me
 */

public class MessengerService extends Service {

    private final Messenger messenger = new Messenger(new MessengerHandler());


    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MainActivity.MSG_FROM_CLIENT:
                    //2、服务端接送消息
                    Log.d("wxl", "msg=" + msg.getData().getString("msg"));

                    //4、服务端回复消息给客户端
                    Messenger serviceMessenger = msg.replyTo;
                    Message replyMessage = Message.obtain(null, MSG_FROM_SERVICE);
                    Bundle bundle = new Bundle();
                    bundle.putString("msg", "Hello from service.");
                    replyMessage.setData(bundle);
                    try {
                        serviceMessenger.send(replyMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}

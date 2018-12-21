package com.wuxiaolong.androidprocesssample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final int MSG_FROM_CLIENT = 1000;
    public static final int MSG_FROM_SERVICE = 1001;
    private Messenger clientMessenger;
    private ServiceConnection messengerServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //1、发送消息给服务端
            clientMessenger = new Messenger(service);
            Message message = Message.obtain(null, MSG_FROM_CLIENT);
            Bundle bundle = new Bundle();
            bundle.putString("msg", "Hello from client.");
            message.setData(bundle);
            //3、这句是服务端回复客户端使用
            message.replyTo = getReplyMessenger;
            try {
                clientMessenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private final Messenger getReplyMessenger = new Messenger(new MessengerHandler());

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MainActivity.MSG_FROM_SERVICE:
                    //5、服务端回复消息给客户端，客户端接送消息
                    Log.d("wxl", "msg=" + msg.getData().getString("msg"));
                    break;
            }
            super.handleMessage(msg);
        }
    }

    private ServiceConnection aidlServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IUserManager remoteService = IUserManager.Stub.asInterface(service);
            UserBean userBean = new UserBean();
            userBean.setUserId(1);
            userBean.setUserName("WuXiaolong");
            try {
                remoteService.getUser(userBean);
                remoteService.hello("Hello");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 文件共享
        SingletonUtil singletonUtil = SingletonUtil.getInstance();
        singletonUtil.setUserId("007");
        AndroidUtil.writeObjectToSDCard(singletonUtil, SingletonUtil.ROOT_FILE_DIR, SingletonUtil.USER_STATE_FILE_NAME_DIR);

        // Messenger 进行通信
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, messengerServiceConnection, Context.BIND_AUTO_CREATE);

        //AIDL
        intent = new Intent(this, AIDLService.class);
        bindService(intent, aidlServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(messengerServiceConnection);
        unbindService(aidlServiceConnection);
        super.onDestroy();
    }

    public void onProcess(View view) {
        startActivity(new Intent(this, Process1Activity.class));
        //startActivity(new Intent(this, Process2Activity.class));
    }
}

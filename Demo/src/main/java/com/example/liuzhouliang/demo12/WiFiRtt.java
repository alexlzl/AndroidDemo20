package com.example.liuzhouliang.demo12;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.net.wifi.rtt.RangingRequest;
import android.net.wifi.rtt.RangingResult;
import android.net.wifi.rtt.RangingResultCallback;
import android.net.wifi.rtt.WifiRttManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.List;

/**
 * @ describe Created by huangwangjian on 2018/5/7.
 *  * 功能描述: WIFI RTT 室内定位测试类
 *
 * @author lzl
 *
 * @ time ] 11:23
 *
 * @ param
 *
 * @ return
 */

public class WiFiRtt {

    private Context mContext;

    public WiFiRtt(Context c){
        this.mContext = c;
        IntentFilter wifiFileter = new IntentFilter();
        wifiFileter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        wifiFileter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        wifiFileter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        mContext.registerReceiver(new WifiChangeReceiver(), wifiFileter);
    }

    public class WifiChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)){
                useWifiRtt();
            }
        }
    }


    private WifiManager mWifiManager;
    private WifiRttManager mWifiRttManager;
    private WifiRttHandler mWifiRttHandler;

   /**
    * @ describe 室内定位主要使用WifiRttManager.java
    *      * 该类主要利用 IEEE802.11mc Wi-Fi Round Trip Time (RTT) technology去侦测手机和Ap之间的距离
    *      * 主要api为 startRanging,其作用是测量AP的距离,因为测试需要对多个ap侦测,是一个批量侦测行为
    *      * 顾我们会用到以下Api:addAccessPoint(ScanResult)和addAccessPoints(List)来表示侦测这些AP
    *      *
    *      * startRanging接口Requires the ACCESS_COARSE_LOCATION, CHANGE_WIFI_STATE and ACCESS_WIFI_STATE permissions.
    *      * startRanging接口参数详解
    *      * 第一个参数:表示发起测距请求,必须不为null
    *      * 第二个参数:表示请求有结果之后的回调
    *
    * @author lzl
    *
    * @ time 2018/12/17 11:24
    *
    * @ param
    *
    * @ return
    */
    @SuppressLint("WrongConstant")
    private void useWifiRtt(){
        mWifiRttHandler = new WifiRttHandler();
        mWifiManager = (WifiManager)mContext.getSystemService(Context.WIFI_SERVICE);
        boolean hasRtt = mContext.getPackageManager().hasSystemFeature(PackageManager.FEATURE_WIFI_RTT);
        Log.d("hwj","**useWifiRtt**" + hasRtt);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            mWifiRttManager = (WifiRttManager)mContext.getSystemService(Context.WIFI_RTT_RANGING_SERVICE);
            RangingRequest.Builder builder = new RangingRequest.Builder();
            if(mWifiManager != null){
                builder.addAccessPoints(mWifiManager.getScanResults());
            }
            if(mWifiRttManager != null){
                mWifiRttManager.startRanging(builder.build(),new RangingResultCallback(){

                    @Override
                    public void onRangingFailure(int i) {

                    }

                    /**
                     * @param list:返回的测距的ap
                     */
                    @Override
                    public void onRangingResults(List<RangingResult> list) {

                    }
                },mWifiRttHandler);
            }
        }
    }

    private class WifiRttHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }
}


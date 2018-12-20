package com.example.accountmanager;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

/**
 * date：2017/9/19 on 11:18
 * description: 用于账户的同步功能,同步框架用的Content Privder框架协作,同时需要SyncAdapter做支持，否则崩溃
 * 账号同步请参考：
 * http://www.jianshu.com/p/dc9a2693478e
 * http://blog.csdn.net/javensun/article/details/41984373
 */

/**
 * SyncAdapter不会自动做数据传输，它只是封装你的代码，以便框架可以在后台调用，而不需要你的应用介入。同步框架准备要同步应用数据的时候，
 * 它会调用SyncAdapter中实现的onPerformSync()方法。应该通过定期任务或是根据一些事件的结果来运行SyncAdapter。比如，隔一段时间或在
 * 每天某个特殊的时间运行，或是在本地数据变化后运行。调用时机如下：
 * <p>
 * 1.服务端数据变化时
 * 服务端数据变化时，根据服务端发送的消息运行。这样可以避免轮询服务器影响性能和功耗。
 * 2.本地数据变化时
 * 本地数据变化后同步可以将本地变化的数据发送到服务端，适合用来确保服务端数据最新。如果数据真的是用ContentProvider保存的，那这种方式是很容易实现的（译者注：在ContentProvider中使用ContentResolver的 notifyChange(android.net.Uri, android.database.ContentObserver, boolean)方法）；如果是伪造的ContentProvider，那可能要麻烦一些。
 * 3.系统发送网络消息时
 * 当系统发出保持TCP/IP连接开启的网络消息时发起，这个网络消息是网络框架的一部分。这是自动同步的一种方式，可以考虑和基于时间间隔的同步结合起来使用。
 * 4.固定时间间隔
 * 自定义一个固定的时间间隔，或者是每天的某个时间点发起
 * 5.即时发起
 * 由用户手动操作发起。但是，为了有更好的体验，最好还是以自动同步为主，这样可以降低电池和网络资源的消耗。
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {

    private ContentResolver contentResolver;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        contentResolver = context.getContentResolver();
    }

    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        contentResolver = context.getContentResolver();
    }

    /**
     * 该方法是在子线程中执行
     *
     * @param account               与本次触发事件关联的Account对象，如果你的服务器不需要账号，直接无视就可以。
     * @param bundle                包含一些标志位的Bundle 对象
     * @param s                     系统中ContentProvider的authority，一般是你自己应用中的ContentProvider对应的authority。
     * @param contentProviderClient authority对应的ContentProviderClient，它是ContentProvider的一个轻量接口，具有与ContentResolver相同的功能。
     *                              如果是用ContentProvider保存的数据，你可以用这个对象连接到ContentProvider，否则无视就好。
     * @param syncResult            SyncResult对象，可以用来将同步的结果传给同步框架。
     */
    @Override
    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        //操作步骤
        /**
         1.连接服务器
         虽然同步开始时你可以认为网络是通畅的，但是同步框架并不会自动帮你连接服务器
         2.下载上传数据
         SyncAdapter不会自动做数据传输。如果你要从服务端取数据存到本地，那你必须提供请求、下载、插入数据的代码。同样，如果需要上传数据
         ，也要读数据、发送数据请求。除此之外，还需要处理数据传输中发生的网络错误。
         3.处理数据冲突
         SyncAdapter不会自动处理服务端和本地的数据冲突。而且，也不会检测本地和服务端的数据哪一个更新。你必须自己提供算法处理这种场景。
         4.清理
         在传输结束后关闭与服务器的链接，清理临时文件和缓存。
         注意： 同步框架自动将  onPerformSync()放在后台线程，因此不需要自己设置后台运行。
         */
        Log.e("SyncAdapter", "onPerformSync方法调用了");
    }
}

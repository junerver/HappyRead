package com.junerver.happyread;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

import cn.bmob.v3.Bmob;

/**
 * Created by junerver on 2016/10/25.
 */
public class HappyReadApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化 Bmob SDK
        Bmob.initialize(this, Constants.BMOB_APPID);
        //初始化内存泄露检测库
        LeakCanary.install(this);
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }

}

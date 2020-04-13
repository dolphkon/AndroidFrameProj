package com.example.frameproj;

import android.app.Application;
import android.content.Context;

import com.xuexiang.xui.XUI;
import com.yechaoa.yutils.ActivityUtil;
import com.yechaoa.yutils.LogUtil;
import com.yechaoa.yutils.YUtils;

public class FrameApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        XUI.init(this); //初始化UI框架
        //初始化
        YUtils.initialize(this);
        //设置打印开关
        LogUtil.setIsLog(true);
        //注册Activity生命周期
        registerActivityLifecycleCallbacks(ActivityUtil.getActivityLifecycleCallbacks());
    }
    public static Context getContext() {
        return mContext;
    }
}

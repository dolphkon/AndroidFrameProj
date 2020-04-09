package com.example.frameproj;

import android.app.Application;
import android.content.Context;

import com.xuexiang.xui.XUI;

public class FrameApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        XUI.init(this); //初始化UI框架
    }
    public static Context getContext() {
        return mContext;
    }
}

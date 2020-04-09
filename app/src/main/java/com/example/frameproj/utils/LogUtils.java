package com.example.frameproj.utils;


import com.example.frameproj.BuildConfig;
import com.orhanobut.logger.Logger;


public class LogUtils {

    static boolean isDebug = BuildConfig.DEBUG;

    public static void i(String msg) {
        if (isDebug) {
            Logger.i(msg);
        }
    }
    public static void i(String TAG, String msg) {
        if (isDebug) {
            Logger.i(TAG+" ==> "+msg);
        }
    }

    public static void v(String msg) {
        if (isDebug) {
            Logger.v(msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Logger.e(msg);
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Logger.d(msg);
        }
    }

    public static void w(String msg) {
        if (isDebug) {
            Logger.w(msg);
        }
    }


    public static void v(String TAG, String msg) {
        if (isDebug) {
            Logger.v(TAG+" ==> "+msg);
        }
    }

    public static void e(String TAG, String msg) {
        if (isDebug) {
            Logger.e(TAG+" ==> "+msg);
        }
    }

    public static void d(String TAG, String msg) {
        if (isDebug) {
            Logger.d(TAG+" ==> "+msg);
        }
    }

    public static void w(String TAG, String msg) {
        if (isDebug) {
            Logger.w(TAG+" ==> "+msg);
        }
    }

    public static void json(String msg){
        if(isDebug){
            Logger.json(msg);
        }
    }


}

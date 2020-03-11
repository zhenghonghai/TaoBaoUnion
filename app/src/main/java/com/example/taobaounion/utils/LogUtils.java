package com.example.taobaounion.utils;

import android.util.Log;

public class LogUtils {
    private static int currentLev = 4;
    private static final int DEBUG_LVE = 4;
    private static final int INFO_LVE = 3;
    private static final int WARNING_LVE = 2;
    private static final int ERROR_LVE = 1;

    public static void d(Object object, String log) {
        if (currentLev >= DEBUG_LVE) {
            Log.d(object.getClass().getSimpleName(), log);
        }
    }
    public static void i(Object object, String log) {
        if (currentLev >= INFO_LVE) {
            Log.i(object.getClass().getSimpleName(), log);
        }
    }
    public static void w(Object object, String log) {
        if (currentLev >= WARNING_LVE) {
            Log.w(object.getClass().getSimpleName(), log);
        }
    }
    public static void e(Object object, String log) {
        if (currentLev >= ERROR_LVE) {
            Log.e(object.getClass().getSimpleName(), log);
        }
    }
}

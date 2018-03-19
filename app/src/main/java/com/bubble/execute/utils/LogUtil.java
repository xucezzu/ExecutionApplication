package com.bubble.execute.utils;

import android.util.Log;

import java.util.Objects;

/**
 * @Author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/2/5
 * 版权所有 © 徐长策
 * <p>
 * 此工具类为日志打印；
 * 根据不同的显示确定打印级别；
 * 如果生产模式可以根据情况关掉打印；
 * </p>
 */

public class LogUtil {
    /**
     * 该变量为是否是调试模式
     */
    private static final boolean IS_DEBUG = true;
    /**
     * 所在的类名
     */
    private static String className;
    /**
     * 所在的方法名
     */
    private static String methodName;
    /**
     * 所在行号
     */
    private static int lineNumber;
    /**
     * 1 --> 显示Verbose及以上的Log
     * 2 --> 显示Debug及以上的Log
     * 3 --> 显示Info及以上的Log
     * 4 --> 显示Warn及以上的Log
     * 5 --> 显示Error及以上的Log
     */
    private static final int VERBOSE = 1;
    private static final int DEBUG = 2;
    private static final int INFO = 3;
    private static final int WARN = 4;
    private static final int ERROR = 5;
    /**
     * 控制显示的级别
     */
    private static final int LEVEL = VERBOSE;

    /**
     * 输出i等级log
     */
    public static void i(String tag, String msg) {
        if (IS_DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void i(Objects objects, String msg) {
        if (IS_DEBUG) {
            Log.i(objects.getClass().getSimpleName(), msg);
        }
    }

    /**
     * 输出e等级log
     */
    public static void e(String tag, String msg) {
        if (IS_DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void e(Objects objects, String msg) {
        if (IS_DEBUG) {
            Log.e(objects.getClass().getSimpleName(), msg);
        }
    }

    /**
     * 输出d等级log
     */
    public static void d(String tag, String msg) {
        if (IS_DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void d(Objects objects, String msg) {
        if (IS_DEBUG) {
            Log.d(objects.getClass().getSimpleName(), msg);
        }
    }

    /**
     * 输出w等级log
     */
    public static void w(String tag, String msg) {
        if (IS_DEBUG) {
            Log.w(tag, msg);
        }
    }

    public static void w(Objects objects, String msg) {
        if (IS_DEBUG) {
            Log.w(objects.getClass().getSimpleName(), msg);
        }
    }

    /**
     * 输出v等级log
     */
    public static void v(String tag, String msg) {
        if (IS_DEBUG) {
            Log.v(tag, msg);
        }
    }

    public static void v(Objects objects, String msg) {
        if (IS_DEBUG) {
            Log.v(objects.getClass().getSimpleName(), msg);
        }
    }

    private static String createLog(String log) {
        return "BUBBLE:[" + methodName + ":" + lineNumber + "]" + log;
    }

    public static boolean isDebuggable() {
        return true;
    }

    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }

    public static void v(String message) {
        if (!isDebuggable()) {
            return;
        }

        if (LEVEL <= VERBOSE) {
            getMethodNames(new Throwable().getStackTrace());
            Log.v(className, createLog(message));
        }
    }

    public static void d(String message) {
        if (!isDebuggable()) {
            return;
        }
        if (LEVEL <= DEBUG) {
            getMethodNames(new Throwable().getStackTrace());
            Log.d(className, createLog(message));
        }
    }

    public static void i(String message) {
        if (!isDebuggable()) {
            return;
        }

        if (LEVEL <= INFO) {
            getMethodNames(new Throwable().getStackTrace());
            Log.i(className, createLog(message));
        }
    }

    public static void w(String message) {
        if (!isDebuggable()) {
            return;
        }

        if (LEVEL <= WARN) {
            getMethodNames(new Throwable().getStackTrace());
            Log.w(className, createLog(message));
        }
    }

    public static void e(String message) {
        if (!isDebuggable()) {
            return;
        }

        if (LEVEL <= ERROR) {
            getMethodNames(new Throwable().getStackTrace());
            Log.e(className, createLog(message));
        }
    }
}

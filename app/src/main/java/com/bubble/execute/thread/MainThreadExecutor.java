package com.bubble.execute.thread;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/4/2
 * 版权所有 © 徐长策
 */
public class MainThreadExecutor implements Executor{

    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(@NonNull Runnable command) {
        handler.post(command);
    }
}

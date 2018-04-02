package com.bubble.execute.thread;

import android.os.Process;
import android.support.annotation.NonNull;

import java.util.concurrent.ThreadFactory;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/30
 * 版权所有 © 徐长策
 */
public class PriorityThreadFactory implements ThreadFactory{
    /**
     * 线程的优先级
     */
    private final int mThreadPriority;

    PriorityThreadFactory(int threadPriority){
        this.mThreadPriority = threadPriority;
    }

    @Override
    public Thread newThread(@NonNull final Runnable runnable) {
        Runnable wrapperRunnable = new Runnable() {
            @Override
            public void run() {
                Process.setThreadPriority(mThreadPriority);
                runnable.run();
            }
        };
        return new Thread(wrapperRunnable);
    }
}

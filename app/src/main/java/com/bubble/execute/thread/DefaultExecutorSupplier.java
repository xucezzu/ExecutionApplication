package com.bubble.execute.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/4/2
 * 版权所有 © 徐长策
 */
public class DefaultExecutorSupplier {
    /**
     * 核心线程数量
     */
    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    /**
     * 后台任务Executor
     */
    private final ThreadPoolExecutor mForBackgroundTasks;

    /**
     * 轻量级后台任务Executor
     */
    private final ThreadPoolExecutor mForLightWeightBackgroundTasks;

    /**
     * 主线程任务Executor
     */
    private final Executor mMainThreadExecutor;

    /**
     * DefaultExecutorSupplier单例对象
     */
    private static DefaultExecutorSupplier sInstance;

    /**
     * 返回一个DefaultExecutorSupplier单例
     */
    public static DefaultExecutorSupplier getInstance(){
        if(sInstance == null){
            synchronized (DefaultExecutorSupplier.class){
                sInstance = new DefaultExecutorSupplier();
            }
        }
        return sInstance;
    }

    /**
     * DefaultExecutorSupplier构造函数
     */
    private DefaultExecutorSupplier() {
        // 设置线程工厂对象
        ThreadFactory backgroundPriorityThreadFactory = new PriorityThreadFactory(1);

        // 初始化mForBackgroundTasks
        mForBackgroundTasks = new PriorityThreadPoolExecutor(
                NUMBER_OF_CORES * 2,
                NUMBER_OF_CORES * 2,
                60L,
                TimeUnit.SECONDS,
                backgroundPriorityThreadFactory
        );

        // 初始化mForLightWeightBackgroundTasks
        mForLightWeightBackgroundTasks = new PriorityThreadPoolExecutor(
                NUMBER_OF_CORES * 2,
                NUMBER_OF_CORES * 2,
                60L,
                TimeUnit.SECONDS,
                backgroundPriorityThreadFactory
        );

        // 初始化mMainThreadExecutor
        mMainThreadExecutor = new MainThreadExecutor();
    }

    /**
     * 返回后台任务Executor
     */
    public ThreadPoolExecutor forBackgroundTasks() {
        return mForBackgroundTasks;
    }

    /**
     * 返回轻量级的Executor
     */
    public ThreadPoolExecutor forLightWeightBackgroundTasks() {
        return mForLightWeightBackgroundTasks;
    }

    /**
     * 返回主线程Executor
     */
    public Executor forMainThreadTasks() {
        return mMainThreadExecutor;
    }
}

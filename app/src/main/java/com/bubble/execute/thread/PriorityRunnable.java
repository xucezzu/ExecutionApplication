package com.bubble.execute.thread;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/4/2
 * 版权所有 © 徐长策
 *
 */
public class PriorityRunnable implements Runnable{
    private final Priority priority;

    public PriorityRunnable(Priority priority) {
        this.priority = priority;
    }

    @Override
    public void run() {
    }

    Priority getPriority() {
        return priority;
    }
}

package com.bubble.execute.thread;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/4/2
 * 版权所有 © 徐长策
 * 线程的等级
 * <注>：不允许修改这些常量的顺序. 为了确保能按正确的顺序执行.
 */
public enum Priority {
    /**
     * 低等级. 用于读取数据
     */
    LOW,

    /**
     * 中等级，用于很快就能获取到数据
     */
    MEDIUM,

    /**
     * 高等级. 用于几乎马上就能得到数据
     */
    HIGH,

    /**
     * 高等级，用于能立即得到数据
     */
    IMMEDIATE
}

package com.melody.chapter1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 使用线程局部变量 变量与对应线程绑定
 * 
 * @author Administrator
 *
 */

public class ThreadLocal02 {
    public static void main(String[] args) {
        SafeTask task = new SafeTask();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(task);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SafeTask implements Runnable {
    private static ThreadLocal<Date> startDate = new ThreadLocal<Date>() {
        protected Date initialValue() {
            return new Date();
        }
    };

    @Override
    public void run() {
        System.out.printf("开始线程: %s:%s\n", Thread.currentThread().getId(),
                startDate.get());
        try {
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("线程结束: %s: %s\n", Thread.currentThread().getId(),
                startDate.get());
    }

}
package com.melody.chapter1;

import java.util.Random;
/**
 * 线程组中不可控异常的处理
 * 建立一个方法来捕获线程组中的任何对象抛出的异常
 * @author Administrator
 *当前程抛出非捕获异常时，JVM寻找3中可能的处理器
 *1.寻找抛出这个异常的非捕获异常处理器
 *2.查找这个线程所在组的非捕获异常处理器
 *3.寻找默认的非捕获异常处理器
 *
 */
public class DealGroupException {
    public static void main(String[] args) {
        MyThreadGroup group = new MyThreadGroup("我的线程组");
        Task1 task1 = new Task1();
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(group, task1);
            thread.start();
        }
    }
}

class MyThreadGroup extends ThreadGroup {
    public MyThreadGroup(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("Thread %s has thrown an Exception\n", t.getId());
        e.printStackTrace(System.out);
        System.out.printf("Terminating the rest of the Threads\n");
        interrupt();
    }
}

class Task1 implements Runnable {

    @Override
    public void run() {
        int result;
        Random random = new Random(Thread.currentThread().getId());
        while (true) {
            result = 1000 / ((int) (random.nextDouble() * 10000));
            System.out.printf("%s : %d\n", Thread.currentThread().getId(),
                    result);
            if (Thread.currentThread().isInterrupted()) {
                System.out.printf("%d :Interrupted\n", Thread.currentThread()
                        .getId());
                return;
            }
        }
    }

}

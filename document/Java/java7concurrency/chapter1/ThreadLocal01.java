package com.melody.chapter1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 不使用线程局部变量
 * 变量全局共享
 * @author Administrator
 *
 */

public class ThreadLocal01 {
    public static void main(String[] args) {
        UnsafeTask task=new UnsafeTask();
        for(int i=0;i<100;i++){
            Thread thread=new Thread(task);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class UnsafeTask implements Runnable{
    private Date startDate;
    @Override
    public void run() {
        startDate=new Date();
        System.out.printf("开始线程: %s:%s\n",Thread.currentThread().getId(),startDate);
        try {
            TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("线程结束: %s: %s\n",Thread.currentThread().getId(),startDate);
    }
    
}
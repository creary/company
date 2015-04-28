package com.melody.chapter2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock vs synchronized
 * 
 * @author Administrator 1.synchronize只能在同一个代码块中释放和获取控制，lock有更灵活的同步代码块结构
 *         2.Lock更多的功能 比如tryLock 死锁 3.Lock支持读写分离 4.Lock性能更好
 *  修改锁的公平性
 *         以下模仿打印队列
 */
public class FairLockSync {
    public static void main(String[] args) {
        PrintQ printQueue=new PrintQ();
        Thread thread []=new Thread [10];
        for(int i=0;i<10;i++){
            thread[i]=new Thread(new Jobjob(printQueue),"Thread "+i);
        }
        for(int i=0;i<10;i++){
            thread[i].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class PrintQ {
    //此时有很多线程在等待锁时，选择的是等待时间最长的
    private final Lock queueLock = new ReentrantLock(true);

    public synchronized void printJob(Object document) {
        queueLock.lock();
        // 模仿文档打印
        try {
        long duration = (long) (Math.random() * 10000);
        System.out.println(Thread.currentThread().getName()
                + ":PrintQueue:Printing a Job during " + (duration / 1000)
                + " seconds");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            queueLock.unlock();
        }
        
        queueLock.lock();
        // 模仿文档打印
        try {
        long duration = (long) (Math.random() * 10000);
        System.out.println(Thread.currentThread().getName()
                + ":PrintQueue:Printing a Job during " + (duration / 1000)
                + " seconds");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            queueLock.unlock();
        }
        
    }
}
class Jobjob implements Runnable{
    private PrintQ printQueue;
    public Jobjob(PrintQ printQueue) {
        this.printQueue=printQueue;
    }
    @Override
    public void run() {
        printQueue.printJob(new Object());
        System.out.printf("%s: The document has been printed\n",Thread.currentThread().getName());
    }
}
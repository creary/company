package com.melody.chapter2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock vs synchronized
 * 
 * @author Administrator 1.synchronize只能在同一个代码块中释放和获取控制，lock有更灵活的同步代码块结构
 *         2.Lock更多的功能 比如tryLock 死锁 3.Lock支持读写分离 4.Lock性能更好
 *
 *         以下模仿打印队列
 */
public class LockSync {
    public static void main(String[] args) {
        PrintQueue printQueue=new PrintQueue();
        Thread thread []=new Thread [10];
        for(int i=0;i<10;i++){
            thread[i]=new Thread(new Job(printQueue),"Thread "+i);
        }
        for(int i=0;i<10;i++){
            thread[i].start();
        }
    }
}

class PrintQueue {
    private final Lock queueLock = new ReentrantLock();

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
    }
}
class Job implements Runnable{
    private PrintQueue printQueue;
    public Job(PrintQueue printQueue) {
        this.printQueue=printQueue;
    }
    @Override
    public void run() {
        System.out.printf("%s: Going to print a document\n",Thread.currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf("%s: The document has been printed\n",Thread.currentThread().getName());
    }
}
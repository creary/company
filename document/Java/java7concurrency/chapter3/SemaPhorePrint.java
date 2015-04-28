package com.melody.chapter3;

import java.util.concurrent.Semaphore;
/**
 * 在测试下都是公平的
 * @author Administrator
 *资源并发访问控制
 */
public class SemaPhorePrint {
    public static void main(String[] args) {
        PrintQueue printQueue=new PrintQueue();
        Thread [] thread=new Thread[10];
        for(int i=0;i<10;i++){
            thread[i]=new Thread(new Job(printQueue), "Thread"+i);
        }
        for(int i=0;i<10;i++){
            thread[i].start();
        }
    }
}
class PrintQueue{
    private final Semaphore semaphore;
    public PrintQueue() {
        semaphore=new Semaphore(1);
    }
    public void PrintJob(Object document){
        try {
            semaphore.acquire();//获取信号量
            long duration=(long)(Math.random()*1000);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n",Thread.currentThread().getName(),duration);
            Thread.sleep(duration/1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            semaphore.release();//释放信号
        }
    }
}
class Job implements Runnable{
    private PrintQueue printQueue;
    public Job(PrintQueue printQueue){
        this.printQueue=printQueue;
    }
    @Override
    public void run() {
        System.out.printf("%s :Going to print a job\n",Thread.currentThread().getName());
        printQueue.PrintJob(new Object());
        System.out.printf("%s: The ducument has been printed\n",Thread.currentThread().getName());
    }
    
}
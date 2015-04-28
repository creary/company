package com.melody.chapter3;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 信号量保护一个资源的多个副本
 * @author Administrator
 *
 */
public class ThreePrinted {
    public static void main(String[] args) {
        PrintQueue1 printQueue1=new PrintQueue1();
        Thread [] thread=new Thread[10];
        for(int i=0;i<10;i++){
            thread[i]=new Thread(new Job1(printQueue1), "Thread"+i);
        }
        for(int i=0;i<10;i++){
            thread[i].start();
        }
    }
}
class PrintQueue1{
    private final Semaphore semaphore;
    private boolean [] freePrinters;
    private Lock lockprint;
    public PrintQueue1() {
        semaphore=new Semaphore(3);
        freePrinters=new boolean[3];
        for(int i=0;i<3;i++){
            freePrinters[i]=true;
        }
        lockprint=new ReentrantLock();
    }
    public void PrintJob(Object document){
        try {
            semaphore.acquire();//获取信号量
            int assignedPrinter=getPrinter();
            long duration=(long)(Math.random()*10);
            System.out.printf("%s: PrintQueue: Printing a Job in Printer %d during %d seconds\n",
                    Thread.currentThread().getName(),assignedPrinter,duration);
            TimeUnit.SECONDS.sleep(duration);
            freePrinters[assignedPrinter]=true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            semaphore.release();//释放信号
        }
    }
    public int getPrinter() {
        int ret=-1;
        lockprint.lock();
        for(int i=0;i<freePrinters.length;i++){
            if(freePrinters[i]){
                ret=i;
                freePrinters[i]=false;
                break;
            }
        }
        lockprint.unlock();
        return ret;
    }
}
class Job1 implements Runnable{
    private PrintQueue1 printQueue;
    public Job1(PrintQueue1 printQueue1){
        this.printQueue=printQueue1;
    }
    @Override
    public void run() {
        System.out.printf("%s :Going to print a job\n",Thread.currentThread().getName());
        printQueue.PrintJob(new Object());
        System.out.printf("%s: The ducument has been printed\n",Thread.currentThread().getName());
    }
    
}

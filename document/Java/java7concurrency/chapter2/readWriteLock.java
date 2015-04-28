package com.melody.chapter2;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class readWriteLock {
    public static void main(String[] args) {
        PriceInfo info=new PriceInfo();
        Reader [] readers=new Reader[5];
        Thread [] threads=new Thread[5];
        for(int i=0;i<5;i++){
            readers[i]=new Reader(info);
            threads[i]=new Thread(readers[i]);
        }
        Writer writer=new Writer(info);
        Thread thread=new Thread(writer);
        for(int i=0;i<5;i++){
            threads[i].start();
        }
        thread.start();
    }
}
class PriceInfo{
    private double price1;
    private double price2;
    private ReadWriteLock lock;
    public PriceInfo() {
        price1=1.0;
        price2=2.0;
        lock=new ReentrantReadWriteLock();
    }
    public double getPrice1(){
        lock.readLock().lock();
        double value=price1;
        lock.readLock().unlock();
        return value;
    }
    public double getPrice2(){
        lock.readLock().lock();
        double value=price2;
        lock.readLock().unlock();
        return value;
    }
    public void setPrices(double price1,double price2){
        lock.writeLock().lock();
        this.price1=price1;
        this.price2=price2;
        lock.writeLock().unlock();
    }
}
class Reader implements Runnable{
    private PriceInfo info;
    public Reader(PriceInfo info) {
        this.info=info;
    }
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.printf("%s : Price 1: %f\n",Thread.currentThread().getName(),info.getPrice1());
            System.out.printf("%s : Price 2: %f\n",Thread.currentThread().getName(),info.getPrice2());
        }
    }
}

class Writer implements Runnable{
    private PriceInfo info;
    public Writer(PriceInfo info) {
        this.info=info;
    }
    @Override
    public void run() {
        for(int i=0;i<3;i++){
            System.out.printf("Writer: attempt to modify the price.\n");
            info.setPrices(Math.random()*10, Math.random()*8);
            System.out.printf("Writer: Price have been modified.\n");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}





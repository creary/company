package com.melody.chapter2;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * wait notify notifyAll
 * @author Administrator
 *生产者和消费者都要从数据存储域中取数据
 */
public class Producer_Consumer {
    public static void main(String[] args) {
        EventStorage storage=new EventStorage();
        Producer producer=new Producer(storage);
        Consumer consumer=new Consumer(storage);
        Thread thread1=new Thread(producer);
        Thread thread2=new Thread(consumer);
        thread1.start();
        thread2.start();
    }
}
class EventStorage{
    private int maxSize;
    private List<Date> storage;
    public EventStorage() {
        maxSize=10;
        storage=new LinkedList<>();
    }
    public synchronized void set(){
        while(storage.size()==maxSize){
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.println("Set :"+storage.size());
        notifyAll();
    }
    public synchronized void get(){
        while(storage.size()==0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Get: %d :%s",storage.size(),((LinkedList<?>)storage).poll());
        notifyAll();
    }
}
class Producer implements Runnable{
    private EventStorage storage;
    public Producer(EventStorage storage) {
        this.storage=storage;
    }
    @Override
    public void run() {
        for(int i=0;i<100;i++){
            storage.set();
        }
    }
}
class Consumer implements Runnable{
    private EventStorage storage;
    public Consumer(EventStorage storage) {
        this.storage=storage;
    }
    @Override
    public void run() {
        for(int i=0;i<100;i++){
            storage.get();
        }
    }
}
package com.melody.chapter1;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Administrator 1.更容易修改类 2.为有限资源限制创建对象的数目 3.为创建对象生成统计数据
 */
public class threadFactory {
    public static void main(String[] args) {
        MyThreadFactory factory = new MyThreadFactory("我的线程工厂");
        Task2 task2=new Task2();
        Thread thread;
        System.out.println("Starting the Threads");
        for(int i=0;i<10;i++){
            thread=factory.newThread(task2);
            thread.start();
        }
        System.out.println("Factory stats:");
        System.out.printf("%s\n",factory.getStats());
    }
    
}

class MyThreadFactory implements ThreadFactory {
    private int counter;
    private String name;
    private List<String> stats;
    public MyThreadFactory(String name) {
        counter=0;
        this.name=name;
        stats=new ArrayList<String>();
    }
    @Override
    public Thread newThread(Runnable r) {
        Thread t=new Thread(r,name+"-Thread_"+counter);
        counter++;
        stats.add(String.format("Create thread %d with name %s on %s\n", t.getId(),t.getName(),new Date()));
        return t;
    }
    public String getStats(){
        StringBuffer buffer=new StringBuffer();
        Iterator<String> it=stats.iterator();
        while(it.hasNext()){
            buffer.append(it.next());
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
class Task2 implements Runnable{

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

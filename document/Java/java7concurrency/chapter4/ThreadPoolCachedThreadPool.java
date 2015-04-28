package com.melody.chapter4;
/**
 * newCachedThreadPool在线程不够时会为接收到的每一个任务创建一个新的线程
 * 当发送大量的任务且任务需要较长时间时，系统就会超负荷，性能也会下降。
 * 为了避免这个问题，Executors提提供了创建固定大小的线程执行器。
 * newFixedThreadPool,newSingleThreadPool
 */
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolCachedThreadPool {
    public static void main(String[] args) {
        Server server=new Server();
        for(int i=0;i<100;i++){
            Task task=new Task();
            server.executeTask(task);
        }
        server.endServer();
    }
}

class Task implements Runnable {
    private Date initDate;
    
    public Task() {
        initDate=new Date();
    }

    @Override
    public void run() {
        System.out.printf("%s:开始执行 %s\n",Thread.currentThread().getName(),new Date());
        try {
        long duration=(long)(Math.random()*10);
        System.out.printf("%s:执行花的时间是 %d秒\n",Thread.currentThread().getName(),duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Server{
    private ThreadPoolExecutor executor;

    public Server() {
        executor=(ThreadPoolExecutor) Executors.newCachedThreadPool();
    }
    public void executeTask(Task task){
        System.out.println("Server:一个线程开始执行");
        executor.execute(task);
        System.out.println("线程池的大小为:"+executor.getPoolSize());
        System.out.println("Server:活跃的线程数："+executor.getActiveCount());
        System.out.println("Server:完成的任务数量："+executor.getCompletedTaskCount()+"\n");
    }
    public void endServer(){
        executor.shutdown();
    }
}
class A{
    double a;
    public void getdata(){
        if(a==0.0){
            a=2d;    
        }
    }
}
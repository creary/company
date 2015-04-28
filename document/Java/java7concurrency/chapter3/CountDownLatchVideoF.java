package com.melody.chapter3;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * countdownlatch:在完成一组正在其他线程中执行的操作之前，它允许线程一只等待
 * 不是用来保护共享资源或者临界区的，用来同步执行多个任务的一个或者多个线程
 * @author Administrator 
 * 视频会议模拟 等待所有人到来后才开始
 * 
 * 等待多个并发事件的完成
 * 
 * CountDownLatch的三个基本元素：
 * 1.一个初始值，定义必须等待的先行完成的操作数目
 * 2.await方法，需要等待其他时间先完成的线程调用
 * 3.countDown方法，每个被等待的时间在完成的时候调用
 */
public class CountDownLatchVideoF {
    public static void main(String[] args) {
        Videoconference videoconference=new Videoconference(10);
        Thread threadConference=new Thread(videoconference);
        threadConference.start();
        for(int i=0;i<10;i++){
            Participant p=new Participant(videoconference, "Participate"+i);
            Thread t=new Thread(p);
            t.start();
        }
    }
}

// 视频会议类
class Videoconference implements Runnable {
    private CountDownLatch controller;

    public Videoconference(int number) {
        controller = new CountDownLatch(number);
    }

    public void arrive(String name) {
        System.out.println(name + " has arrived");
        controller.countDown();
    }

    @Override
    public void run() {
        System.out.println("还有" + controller.getCount() + "个人还没来");
        try {
            controller.await();
            System.out.println("所有人都来齐了，可以开会了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
//会议参加者类
class Participant implements Runnable{
    private Videoconference conference;
    private String name;
    
    public Participant(Videoconference conference, String name) {
        this.conference = conference;
        this.name = name;
    }

    @Override
    public void run() {
        long duration=(long)(Math.random()*10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        conference.arrive(name);
    }
    
}
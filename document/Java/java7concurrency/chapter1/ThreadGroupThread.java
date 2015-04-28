package com.melody.chapter1;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 创建10个线程并让他们休眠一段时间（模拟一次查询什么之类的） 其中一个执行成功，终端其他9个
 * 
 * @author Administrator
 *
 */
public class ThreadGroupThread {
    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("Searcher");
        Result result = new Result();
        SearchTask searchTask = new SearchTask(result);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(group, searchTask);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("number of threads:%d\n", group.activeCount());
        System.out.printf("Information about the Thread Group\n");
        group.list();

        Thread[] threads = new Thread[group.activeCount()];
        group.enumerate(threads);
        for (int i = 0; i < group.activeCount(); i++) {
            System.out.printf("Thread %s: %s\n", threads[i].getName(),
                    threads[i].getState());
        }
        waitFinish(group);
        group.interrupt();
    }

    private static void waitFinish(ThreadGroup group) {
        while(group.activeCount()>9){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SearchTask implements Runnable {
    private Result result;

    public SearchTask(Result result) {
        this.result = result;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.printf("Thread %s:Start\n", name);
        try {
            doTask();
        } catch (InterruptedException e) {
            System.out.printf("thread %s:interrupted\n", name);
            return;
        }
        result.setName(name);
    }

    private void doTask() throws InterruptedException {
        Random random = new Random(new Date().getTime());
        int value = (int) (random.nextDouble() * 100);
        System.out.printf("Thread: %s: %d\n", Thread.currentThread().getName(),
                value);
        TimeUnit.SECONDS.sleep(value);
    }

}

class Result {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

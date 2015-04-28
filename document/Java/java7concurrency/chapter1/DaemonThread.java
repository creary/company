package com.melody.chapter1;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
/**
 * 守护线程的创建和运行
 * 用作资源管理和清理 
 * @author Administrator
 *
 */
public class DaemonThread {
    public static void main(String[] args) {
        Deque<Event> deque=new ArrayDeque<>();
        WriteTask task=new WriteTask(deque);
        for(int i=0;i<3;i++){
            Thread thread=new Thread(task);
            thread.start();
        }
        CleanerTask cleaner=new CleanerTask(deque);
        cleaner.start();
    }
}

class Event {
    private Date date;
    private String event;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}

class WriteTask implements Runnable {
    private Deque<Event> deque;

    public WriteTask(Deque<Event> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Event event = new Event();
            event.setDate(new Date());
            event.setEvent(String.format(
                    "线程 %s生成了一个Event", Thread
                            .currentThread().getId()));
            deque.addFirst(event);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
//清理后台线程
class CleanerTask extends Thread {
    private Deque<Event> deque;

    public CleanerTask(Deque<Event> deque) {
        this.deque = deque;
        setDaemon(true);//只能在线程执行之前设置
    }

    @Override
    public void run() {
        while (true) {
            Date date = new Date();
            clean(date);
        }
    }

    private void clean(Date date) {
        long difference;
        boolean delete;
        if (deque.size() == 0) {
            return;
        }
        delete = false;
        do {
            //队列中生成的事件事件超过10秒就移除事件
            Event e = deque.getLast();
            difference = date.getTime() - e.getDate().getTime();
            if (difference > 10000) {
                System.out.printf("Cleaner: %s被移除\n", e.getEvent());
                deque.removeLast();
                delete = true;
            }
        } while (difference > 10000);
        if (delete) {
            System.out.printf("Cleanner: size of the queue:%d\n", deque.size());
        }
    }
}
package com.melody.chapter1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 线程的休眠和恢复
 * 
 * @author Administrator
 *
 */
public class SleepThread {
    public static void main(String[] args) {
        FileClock clock = new FileClock();
        Thread thread = new Thread(clock);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}

class FileClock implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s\n", new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                System.out.println("FileClock中断了");
            }
        }
    }
}

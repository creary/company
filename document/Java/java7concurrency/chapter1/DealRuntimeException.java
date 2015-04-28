package com.melody.chapter1;

import java.lang.Thread.UncaughtExceptionHandler;
/**
 * run方法不支持throws语句，因此当一个线程跑出了异常，并且没有被catch(只可能是运行时异常),JVM
 * 会检查改线程是否被预置了未捕获异常处理器。找到就调用  参数为： 线程对象 + 异常参数
 * @author Administrator
 *ps:还有一种方法是将有异常的代码封装成一个方法
 *在run方法外执行就好了
 *
 */
public class DealRuntimeException {
    public static void main(String[] args) {
        Task task=new Task();
        Thread thread=new Thread(task);
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
    }
}
class ExceptionHandler implements UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("一个异常被捕获\n");
        System.out.printf("Thread: %s\n",t.getId());
        System.out.printf("Exception: %s: %s\n",e.getClass().getName(),e.getMessage());
        System.out.printf("Stack Trace: \n");
        e.printStackTrace();
        System.out.printf("Thread status: %s\n",t.getState());
    }
}
class Task implements Runnable{

    @Override
    public void run() {
        int number=Integer.parseInt("TTT");
    }
}
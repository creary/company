package com.melody.chapter1;

import java.util.Date;
import java.util.concurrent.TimeUnit;
/**
 *等待thread1和thread2执行完后主线程才执行剩下的
 *即为等待线程的终结 
 * @author Administrator
 *
 */
public class JoinThread {
    public static void main(String[] args) {
        DataSourceLoader dataSourceLoader=new DataSourceLoader();
        NetSourceLoader loader=new NetSourceLoader();
        Thread thread1=new Thread(dataSourceLoader);
        Thread thread2=new Thread(loader);
        thread1.start();
        thread2.start();
        try {
            thread2.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Main:加载完毕:%s\n",new Date());
    }
}
class DataSourceLoader implements Runnable{

    @Override
    public void run() {
        System.out.printf("数据开始载入资源: %s\n",new Date());
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            System.out.printf("数据资源加载结束: %s\n",new Date());
        }
    }
    
}
class NetSourceLoader implements Runnable{

    @Override
    public void run() {
        System.out.printf("网络开始载入资源: %s\n",new Date());
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            System.out.printf("网络资源加载结束: %s\n",new Date());
        }
    }
}

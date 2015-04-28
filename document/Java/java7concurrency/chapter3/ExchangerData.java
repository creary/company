package com.melody.chapter3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Exchanger允许两个线程到达同步点之后交换数据
 * 但是只能同步两个线程
 * @author Administrator
 *
 */
public class ExchangerData {
    public static void main(String[] args) {
        List<String> buffer1=new ArrayList<String>();
        List<String> buffer2=new ArrayList<String>();
        Exchanger<List<String>> exchanger=new Exchanger<>();
        Producer producer=new Producer(buffer1, exchanger);
        Consumer consumer=new Consumer(buffer2, exchanger);
        Thread threadProducer=new Thread(producer);
        Thread threadConsumer=new Thread(consumer);
        threadProducer.start();
        threadConsumer.start();
    }
}
class Producer implements Runnable{
    private List<String> buffer;
    private final Exchanger<List<String>> exchanger;
    
    public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        int cycle=1;
        for(int i=0;i<10;i++){
            System.out.println("生产者循环第 "+cycle+"次");
            for(int j=0;j<10;j++){
                String message="Event"+((i*10)+j);
                System.out.println("生产者生成消息："+message);
                buffer.add(message);
            }
            try {
                buffer=exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("生产者生产的数据量:"+buffer.size());
            cycle++;
        }
    }
}
class Consumer implements Runnable{
    private List<String> buffer;
    private final Exchanger<List<String>> exchanger;
    
    public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        int cycle=1;
        for(int i=0;i<10;i++){
            System.out.println("消费者循环第 "+cycle+"次");
            try {
                buffer=exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("消费者的数据量:"+buffer.size());
            for(int j=0;j<10;j++){
                String message=buffer.get(0);
                System.out.println("消费者取走的信息："+message);
                buffer.remove(0);
            }
            cycle++;
        }
    }
}
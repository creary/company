package com.melody.chapter1;
/**
 * 线程的中断
 * @author Administrator
 *线程的interrupt被调用时一个Thread的线程中断的属性被置为true
 *isInterrupted（）方法将其返回
 *由于静态方法interrupted()可以自己设置返回值 因此不推荐使用
 */
public class threadInterrupt {
    public static void main(String[] args) {
        Thread task=new Primegenerator();
        task.start();
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        task.interrupt();
    }
}

class Primegenerator extends Thread {
    @Override
    public void run() {
        long number = 1L;
        while (true) {
            if (isPrime(number)) {
                System.out.printf("Number %d is Prime\n", number);
            }
            if(isInterrupted()){
                System.out.println("The Prime Generater has been Interrupted");
                return;
            }
            number++;
        }
    }

    // 是否为质数
    private boolean isPrime(long number) {
        if (number <= 2) {
            return true;
        }
        for (long i = 2; i < number; i++) {
            if (number % Math.sqrt(i) == 0) {
                return false;
            }
        }
        return true;
    }
}

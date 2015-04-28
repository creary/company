package com.melody.chapter2;


public class ThreadSafe {
    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Test test=new Test();
        A a = new A(test);
        B b = new B(test);
        Thread thread1 = new Thread(a);
        Thread thread2 = new Thread(b);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(i);
    }
   
}
class Test{
    private int i=1;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
class A implements Runnable {
    Test test;
    public A(Test test) {
        this.test=test;
    }
    @Override
    public void run() {
        for (int j = 0; j < 10; j++) {
            test.setI(test.getI()+1);
        }
    }
}

class B implements Runnable {
    Test test;
    public B(Test test) {
        this.test=test;
    }
    @Override
    public void run() {
        for (int j = 0; j < 10; j++) {
            test.setI(test.getI()+1);
        }
    }
}

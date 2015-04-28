package com.melody.chapter1;

public class FirstThread {
    public static void main(String[] args) {
        for (int i = 1; i < 11; i++) {
            Calculator2 calculator = new Calculator2(i);
            calculator.start();
        }
    }
}

class Calculator implements Runnable {
    private int number;

    public Calculator(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 1; i < 11; i++) {
            System.out.printf("%s: %d * %d = %d\n", Thread.currentThread()
                    .getName(), number, i, i * number);
        }
    }
}

class Calculator2 extends Thread {
    private int number;

    public Calculator2(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 1; i < 11; i++) {
            System.out.printf("%s: %d * %d = %d\n", Thread.currentThread()
                    .getName(), number, i, i * number);
        }
    }
}

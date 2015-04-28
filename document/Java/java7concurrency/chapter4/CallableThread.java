package com.melody.chapter4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class CallableThread {
    public static void main(String[] args) {

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors
                .newFixedThreadPool(2);
        List<Future<Integer>> resultList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int number = random.nextInt(10);
            FactorialCalculator calculator = new FactorialCalculator(number);
            Future<Integer> result = executor.submit(calculator);
            resultList.add(result);
        }

        do {
            System.out.printf("main:Number of CompletedTask%d",
                    executor.getCompletedTaskCount());
            for (int i = 0; i < resultList.size(); i++) {
                Future<Integer> result = resultList.get(i);
                System.out.printf("Main: Task %d : %s\n", i, result.isDone());
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (executor.getCompletedTaskCount() < resultList.size());

        System.out.println("结果");
        for (int i = 0; i < resultList.size(); i++) {
            Future<Integer> result = resultList.get(i);
            int number;
            try {
                number = result.get();
                System.out.printf("Main:Task %d :%d \n", i, number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}

class FactorialCalculator implements Callable<Integer> {
    private Integer number;

    public FactorialCalculator(Integer number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int result = 1;
        if (number == 0 || number == 1) {
            result = 1;
        } else {
            for (int i = 2; i <= number; i++) {
                result *= i;
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }
        System.out.printf("%s :%d\n", Thread.currentThread().getName(), result);
        return result;
    }

}

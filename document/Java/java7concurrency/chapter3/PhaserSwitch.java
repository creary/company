package com.melody.chapter3;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
/**
 * 考生必须做三道题，只有当所有的学生都完一题时才能继续下一个
 * @author Administrator
 * good code
 */

public class PhaserSwitch {
    public static void main(String[] args) {
        MyPhaser phaser=new MyPhaser();
        Student [] students=new Student[5];
        for(int i=0;i<5;i++){
            students[i]=new Student(phaser);
            phaser.register();
        }
        Thread [] threads=new Thread[students.length];
        for(int i=0;i<threads.length;i++){
            threads[i]=new Thread(students[i], "Student"+i);
            threads[i].start();
        }
        for(int i=0;i<5;i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Phaser结束："+phaser.isTerminated());
    }
}
class MyPhaser extends Phaser{
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch(phase){
        case 0:
            return studentsArrived();
        case 1:
            return finishFirstExercise();
        case 2:
            return finishSecondExercise();
        case 3:
            return finishExam();
        default:
            return true;
        }
    }

    private boolean finishSecondExercise() {
        System.out.println("所有学生完成第二题了");
        System.out.println("开始做第三题");
        return false;
    }

    private boolean finishExam() {
        System.out.println("所有学生都做完了");
        return true;
    }

    private boolean finishFirstExercise() {
        System.out.println("所有学生都做完第一题了");
        System.out.println("该所第二题了");
        return false;
    }

    private boolean studentsArrived() {
        System.out.println("考试即将开始");
        System.out.println("参加考试的学生一共有:"+getRegisteredParties()+"个");
        return false;
    }
}
class Student implements Runnable{
    private Phaser phaser;
    public Student(Phaser phaser) {
        this.phaser=phaser;
    }
    @Override
    public void run() {
        System.out.printf("%s：已经来考试了 %s\n",Thread.currentThread().getName(),new Date());
        phaser.arriveAndAwaitAdvance();
        
        System.out.printf("%s: 去做第一题了 %s\n",Thread.currentThread().getName(),new Date());
        doExercise1();
        System.out.printf("%s: 做完第一题了 %s\n",Thread.currentThread().getName(),new Date());
        phaser.arriveAndAwaitAdvance();
        
        System.out.printf("%s: 去做第二题了 %s\n",Thread.currentThread().getName(),new Date());
        doExercise2();
        System.out.printf("%s: 做完第二题了 %s\n",Thread.currentThread().getName(),new Date());
        phaser.arriveAndAwaitAdvance();
        
        System.out.printf("%s: 去做第三题了 %s\n",Thread.currentThread().getName(),new Date());
        doExercise3();
        System.out.printf("%s: 做完第三题了 %s\n",Thread.currentThread().getName(),new Date());
        phaser.arriveAndAwaitAdvance();
    }
    private void doExercise3() {
        try {
            long duration=(long)(Math.random()*10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void doExercise2() {
        try {
            long duration=(long)(Math.random()*10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void doExercise1() {
        try {
            long duration=(long)(Math.random()*10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
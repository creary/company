package com.melody.chapter3;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 允许多个线程在某一个点上进行同步，之后还可以执行一个线程
 * 
 * 分治编程技术 
 * @author Administrator
 *在数字矩阵中寻找一个数字（使用分治编程技术），
 *把矩阵分成几个子集后在每一个子集中查找
 *
 */
public class CyclicBarrierDivide {
    public static void main(String[] args) {
        final int ROWS=10000;
        final int NUMBERS=1000;
        final int SEARCH=5;
        final int PARTICIPANTS=5;
        final int LINES_PARTICIPANT=2000;
        MatrixMock mock=new MatrixMock(ROWS, NUMBERS, SEARCH);
        Results results=new Results(ROWS);
        Grouper grouper=new Grouper(results);
        CyclicBarrier barrier=new CyclicBarrier(PARTICIPANTS,grouper);
        Searcher [] searcher=new Searcher[PARTICIPANTS];
        for(int i=0;i<PARTICIPANTS;i++){
            searcher[i]=new Searcher(i*LINES_PARTICIPANT,(i*LINES_PARTICIPANT)+LINES_PARTICIPANT,mock,results,5,barrier);
            Thread thread=new Thread(searcher[i]);
            thread.start();
        }
        System.out.println("主线程执行完毕");
    }
}
//矩阵类
class MatrixMock{
    private int [][] data;
    public MatrixMock(int size,int length,int number) {
        int counter=0;
        data=new int[size][length];
        Random random=new Random();
        for(int i=0;i<size;i++){
            for(int j=0;j<length;j++){
                data[i][j]=random.nextInt(10);
                if(data[i][j]==number){
                    counter++;
                }
            }
        }
        System.out.println("矩阵中一共有"+size*length+"个数,要查找的数有"+counter+"个。");
    }
    public int [] getRow(int row){
        if((row>=0) && (row<data.length)){
            return data[row];
        }
        return null;
    }
}
//结果类
class Results{
    //保存矩阵中每行找到指定数的次数
    private int [] data;
    public Results(int size) {
        data=new int[size];
    }
    public void setData(int position,int value){
        data[position]=value;
    }
    public int [] getData(){
        return data;
    }
}
//以上为辅助类

class Searcher implements Runnable{
    private int firstRow;
    private int lastRow;
    private MatrixMock mock;
    private Results results;
    private int number;
    private final CyclicBarrier barrier;
    
    public Searcher(int firstRow, int lastRow, MatrixMock mock,
            Results results, int number, CyclicBarrier barrier) {
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.mock = mock;
        this.results = results;
        this.number = number;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        int counter;//存放每行查找到的次数
        System.out.printf("%s:从 %d 查找到 %d\n",Thread.currentThread().getName(),firstRow,lastRow);
        for(int i=firstRow;i<lastRow;i++){
            int [] row=mock.getRow(i);
            counter=0;
            for(int j=0;j<row.length;j++){
                if(row[j]==number){
                    counter++;
                }
            }
            results.setData(i, counter);
        }
        System.out.printf("%s 线程执行完毕\n",Thread.currentThread().getName());
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
//统计结果
class Grouper implements Runnable{
    private Results results;
    
    public Grouper(Results results) {
        this.results = results;
    }

    @Override
    public void run() {
        int finalResult=0;
        System.out.println("开始统计结果信息.....");
        int [] data=results.getData();
        for(int number:data){
            finalResult+=number;
        }
        System.out.println("结果总数为："+finalResult);
    }
    
}
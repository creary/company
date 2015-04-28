package com.melody.chapter3;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 允许执行并发多任务 Pharser对每一步的结果进行同步，当所有线程执行完时允许执行下一步
 * 一旦调用arriveAndAwaitAdvance()方法，就将调用该方法的线程置于休眠状态，直到所有其他线程到达这个阶段
 * 让所有线程在同一起跑线开始
 * 
 * @author Administrator 
 * 可以动态的增加或减少任务数
 * 不太明白
 */
public class PharserThread {
    public static void main(String[] args) {
        Phaser phaser=new Phaser(3);
        FileSearch system=new FileSearch("C:\\360PhoneInfo", "log", phaser);
        FileSearch apps=new FileSearch("C:\\PerfLogs", "log", phaser);
        FileSearch document=new FileSearch("C:\\MinGW", "log", phaser);
        Thread systemThread=new Thread(system, "System");
        systemThread.start();
        Thread appsThread=new Thread(apps, "apps");
        appsThread.start();
        Thread documentThread=new Thread(document, "document");
        documentThread.start();
        try {
            systemThread.join();
            appsThread.join();
            documentThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Terminated:"+phaser.isTerminated());
    }
}

class FileSearch implements Runnable {
    private String initPath;
    // 存储查找文件的扩展名
    private String end;
    private List<String> results;
    private Phaser phaser;

    public FileSearch(String initPath, String end, Phaser phaser) {
        this.initPath = initPath;
        this.end = end;
        results = new ArrayList<>();
        this.phaser = phaser;
    }
    
    
    private void directoryProcess(File file){
        File [] list=file.listFiles();
        if(list!=null){
            for(int i=0;i<list.length;i++){
                if(list[i].isDirectory()){
                    directoryProcess(list[i]);
                }else{
                    fileProcess(list[i]);
                }
            }
        }
    }
    
    private void fileProcess(File file) {
        if(file.getName().endsWith(end)){
            results.add(file.getAbsolutePath());
        }
    }
    
    private void filterResults(){
        List<String> newResults=new ArrayList<>();
        long actualData=new Date().getTime();
        for(int i=0;i<results.size();i++){
            File file=new File(results.get(i));
            long fileDate=file.lastModified();
            if(actualData-fileDate<TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)){
                newResults.add(results.get(i));
            }
        }
        results=newResults;
    }
    
    private boolean checkResults(){
        if(results.isEmpty()){
            System.out.println(Thread.currentThread().getName()+"线程执行结束 Phaser："+phaser.getPhase());
            phaser.arriveAndDeregister();
            return false;
        }else{
            System.out.printf("%s :Phaser %d:%d results.\n",Thread.currentThread().getName(),phaser.getPhase(),results.size());
            phaser.arriveAndAwaitAdvance();
            return true;
        }
    }
    
    private void showInfo(){
        for(int i=0;i<results.size();i++){
            File file=new File(results.get(i));
            System.out.printf("%s: %s\n",Thread.currentThread().getName(),file.getAbsolutePath());
        }
        phaser.arriveAndAwaitAdvance();
    }
    
    @Override
    public void run() {
        phaser.arriveAndAwaitAdvance();
        System.out.printf("%s: starting\n",Thread.currentThread().getName());
        File file=new File(initPath);
        if(file.isDirectory()){
            directoryProcess(file);
        }
        if(!checkResults()){
            return;
        }
        filterResults();
        if(!checkResults()){
            return;
        }
        showInfo();
        phaser.arriveAndAwaitAdvance();
        System.out.println("线程"+Thread.currentThread().getName()+"执行完成");
    }

}

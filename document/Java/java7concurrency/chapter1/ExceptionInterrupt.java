package com.melody.chapter1;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * 遍历文件夹 用异常来控制线程的中断
 * 
 * @author Administrator
 *
 */
public class ExceptionInterrupt {
    public static void main(String[] args) {
        FileSearch fs=new FileSearch("D:\\", "a.txt");
        Thread task=new Thread(fs);
        task.start();
        try {
            //等待10秒中断线程
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.interrupt();
    }
}

class FileSearch implements Runnable {
    private String initPath;
    private String fileName;

    public FileSearch(String initPath, String fileName) {
        this.initPath = initPath;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        File file = new File(initPath);
        if (file.isDirectory()) {
            try {
                directoyProcess(file);
            } catch (InterruptedException e) {
                System.out.printf("%s: The search has been intereupted", Thread
                        .currentThread().getName());
            }
        }
    }

    private void directoyProcess(File file) throws InterruptedException {
        File[] list = file.listFiles();
        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                if (list[i].isDirectory()) {
                    directoyProcess(list[i]);
                } else {
                    fileProcess(list[i]);
                }
            }
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
    }

    private void fileProcess(File file) throws InterruptedException {
        if (file.getName().equals(fileName)) {
            System.out.printf("%s : %s\n", Thread.currentThread().getName(),
                    file.getAbsolutePath());
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
    }

}

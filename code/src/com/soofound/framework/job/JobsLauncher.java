/*  1:   */package com.soofound.framework.job;
/*  2:   */
/*  3:   */import com.soofound.framework.util.CommonUtil;
/*  4:   */import java.io.PrintStream;
/*  5:   */import java.util.concurrent.Executors;
/*  6:   */
/*  7:   */public final class JobsLauncher
/*  8:   */{
/*  9:   */  private java.util.concurrent.ScheduledExecutorService executor;
/* 10:   */  
/* 11:   */  public void setClazzs(java.util.List<String> clazzs)
/* 12:   */  {
/* 13:13 */    this.executor = Executors.newScheduledThreadPool(clazzs.size());
/* 14:14 */    for (String clazz : clazzs) {
/* 15:   */      try {
/* 16:16 */        ScheduleJob _job = (ScheduleJob)CommonUtil.getInstance(clazz);
/* 17:17 */        this.executor.scheduleAtFixedRate(_job, _job.getDelay(), _job.getPeriod(), _job.getTimeUnit());
/* 18:18 */        System.out.println(_job.getClass().getSimpleName() + " starting...");
/* 19:   */      } catch (Exception e) {
/* 20:20 */        e.printStackTrace();
/* 21:   */      }
/* 22:   */    }
/* 23:   */  }
/* 24:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.job.JobsLauncher
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
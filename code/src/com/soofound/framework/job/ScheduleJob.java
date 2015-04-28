package com.soofound.framework.job;

import java.util.concurrent.TimeUnit;

public abstract interface ScheduleJob
  extends Runnable
{
  public abstract int getDelay();
  
  public abstract int getPeriod();
  
  public abstract TimeUnit getTimeUnit();
}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.job.ScheduleJob
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
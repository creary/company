package com.soofound.cpe.util;

public abstract interface CommandQueue
{
  public abstract void putCommand(int paramInt, String paramString);
  
  public abstract boolean isEmptyCommand(int paramInt);
  
  public abstract void clearCommand(int paramInt);
  
  public abstract String getCommand(int paramInt);
  
  public abstract void putDebugResult(int paramInt, String paramString);
  
  public abstract String getDebugResult(int paramInt);
  
  public abstract String removeDebugResult(int paramInt);
}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.util.CommandQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
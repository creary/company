/*  1:   */package com.soofound.cpe.util;
/*  2:   */
/*  3:   */import java.util.HashMap;
/*  4:   */import java.util.concurrent.ConcurrentHashMap;
/*  5:   */import java.util.concurrent.ConcurrentLinkedQueue;
/*  6:   */
/*  7:   */public class ConcurrentLinkedCommandQueue implements CommandQueue
/*  8:   */{
/*  9: 9 */  private java.util.Map<Integer, ConcurrentLinkedQueue<String>> commands = new HashMap();
/* 10:10 */  private java.util.Map<Integer, String> debugResults = new ConcurrentHashMap();
/* 11:   */  
/* 12:   */  public void putCommand(int hostId, String code)
/* 13:   */  {
/* 14:14 */    ConcurrentLinkedQueue<String> cmds = null;
/* 15:15 */    if (this.commands.containsKey(Integer.valueOf(hostId))) {
/* 16:16 */      cmds = (ConcurrentLinkedQueue)this.commands.get(Integer.valueOf(hostId));
/* 17:   */    } else {
/* 18:18 */      cmds = new ConcurrentLinkedQueue();
/* 19:19 */      this.commands.put(Integer.valueOf(hostId), cmds);
/* 20:   */    }
/* 21:21 */    cmds.add(code);
/* 22:   */  }
/* 23:   */  
/* 24:   */  public boolean isEmptyCommand(int hostId)
/* 25:   */  {
/* 26:26 */    return (this.commands.get(Integer.valueOf(hostId)) == null) || (((ConcurrentLinkedQueue)this.commands.get(Integer.valueOf(hostId))).isEmpty());
/* 27:   */  }
/* 28:   */  
/* 29:   */  public void clearCommand(int hostId)
/* 30:   */  {
/* 31:31 */    this.commands.remove(Integer.valueOf(hostId));
/* 32:32 */    this.debugResults.remove(Integer.valueOf(hostId));
/* 33:   */  }
/* 34:   */  
/* 35:   */  public String getCommand(int hostId)
/* 36:   */  {
/* 37:37 */    if (this.commands.containsKey(Integer.valueOf(hostId))) {
/* 38:38 */      return (String)((ConcurrentLinkedQueue)this.commands.get(Integer.valueOf(hostId))).poll();
/* 39:   */    }
/* 40:40 */    return null;
/* 41:   */  }
/* 42:   */  
/* 43:   */  public void putDebugResult(int hostId, String cmdResult)
/* 44:   */  {
/* 45:45 */    this.debugResults.put(Integer.valueOf(hostId), cmdResult);
/* 46:   */  }
/* 47:   */  
/* 48:   */  public String getDebugResult(int hostId)
/* 49:   */  {
/* 50:50 */    return (String)this.debugResults.get(Integer.valueOf(hostId));
/* 51:   */  }
/* 52:   */  
/* 53:   */  public String removeDebugResult(int hostId)
/* 54:   */  {
/* 55:55 */    return (String)this.debugResults.remove(Integer.valueOf(hostId));
/* 56:   */  }
/* 57:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.util.ConcurrentLinkedCommandQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */
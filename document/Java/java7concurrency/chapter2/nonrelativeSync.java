package com.melody.chapter2;

/**
 * 使用费以来属性同步
 * @author Administrator
 *模仿两个电影院和两个售票处
 *一个售票处卖出的只适合一家电影院
 */
public class nonrelativeSync {
    public static void main(String[] args) {
        Cinema cinema=new Cinema();
        TicketOffice1 office1=new TicketOffice1(cinema);
        TicketOffice2 office2=new TicketOffice2(cinema);
        Thread thread1=new Thread(office1, "售票窗口1");
        Thread thread2=new Thread(office2, "售票窗口2");
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("Room 1 空位:%d \n",cinema.getVacanciesCinema1());
        System.out.printf("Room 2 空位:%d \n",cinema.getVacanciesCinema2());
    }
}
//电影院类
class Cinema{
    private long vavanviesCinema1;
    private long vavanviesCinema2;
    private final Object controlCinema1,controlCinema2;
    public Cinema() {
        controlCinema1=new Object();
        controlCinema2=new Object();
        vavanviesCinema1=20;
        vavanviesCinema2=20;
    }
    //第一家电影院卖票
    public boolean sellTicket1(int number){
        synchronized (controlCinema1) {
         if(number<vavanviesCinema1){
             vavanviesCinema1-=number;
             return true;
         }else{
             return false;
         }
        }
    }
    //第二家电影院卖票
    public boolean sellTicket2(int number){
        synchronized (controlCinema2) {
         if(number<vavanviesCinema2){
             vavanviesCinema2-=number;
             return true;
         }else{
             return false;
         }
        }
    }
    //第一家电影院退票
    public boolean returnTicket1(int number){
        synchronized (controlCinema1) {
             vavanviesCinema1+=number;
             return true;
         }
    }
    //第二家电影院退票
    public boolean returnTicket2(int number){
        synchronized (controlCinema2) {
             vavanviesCinema2+=number;
             return true;
         }
    }
    public long getVacanciesCinema1(){
        return vavanviesCinema1;
    }
    public long getVacanciesCinema2(){
        return vavanviesCinema2;
    }
}
//售票窗口类
class TicketOffice1 implements Runnable{
    private Cinema cinema;
    public TicketOffice1(Cinema cinema) {
        this.cinema=cinema;
    }
    @Override
    public void run() {
        cinema.sellTicket1(3);
        cinema.sellTicket1(2);
        cinema.sellTicket2(2);
        cinema.returnTicket1(3);
        cinema.sellTicket1(5);
        cinema.sellTicket2(2);
        cinema.sellTicket2(2);
        cinema.sellTicket2(2);
    }
}
class TicketOffice2 implements Runnable{
    private Cinema cinema;
    public TicketOffice2(Cinema cinema) {
        this.cinema=cinema;
    }
    @Override
    public void run() {
        cinema.sellTicket2(2);
        cinema.sellTicket2(4);
        cinema.sellTicket1(2);
        cinema.sellTicket1(1);
        cinema.returnTicket2(2);
        cinema.sellTicket1(3);
        cinema.sellTicket2(2);
        cinema.sellTicket1(2);
    }
}

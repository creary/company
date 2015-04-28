package com.melody.chapter2;

public class AccountThreadSafe {
    public static void main(String[] args) {
        Account account=new Account();
        account.setBalance(1000);
        Company company=new Company(account);
        Thread thread=new Thread(company);
        Bank bank=new Bank(account);
        Thread thread2=new Thread(bank);
        System.out.println("初始金额为："+account.getBalance());
        thread.start();
        thread2.start();
        try {
            thread.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("最后的余额为:"+account.getBalance());
    }
}
class Account{
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public synchronized void addAccount(double amount){
        double tmp=balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp+=amount;
        balance=tmp;
    }
    public synchronized void subtractAccount(double amount){
        double tmp=balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp-=amount;
        balance=tmp;
    }
}
class Bank implements Runnable{
    private Account account;
    public Bank(Account account) {
        this.account=account;
    }
    @Override
    public void run() {
        for(int i=0;i<100;i++){
            account.subtractAccount(1000);
        }
    }
}
class Company implements Runnable{
    private Account account;
    public Company(Account account) {
        this.account=account;
    }
    @Override
    public void run() {
        for(int i=0;i<100;i++){
            account.addAccount(1000);
        }
    }
    
}

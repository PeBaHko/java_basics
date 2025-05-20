package org.example;

public class Account {

    private long money;
    private String accNumber;
    private volatile boolean blocking;

    public Account(long money, String accNumber) {
        this.money = money;
        this.accNumber = accNumber;
        this.blocking = false;
    }

    public boolean getStatus() { return blocking; }
    public void Blocked() { blocking = true; }

    public long getMoney() { return money; }
    public void setMoney(long money) { this.money = money; }

    public void addMoney(long money) { this.money += money; }
    public void subMoney(long money) { this.money -= money; }

    public String getAccNumber() { return accNumber; }
    public void setAccNumber(String accNumber) { this.accNumber = accNumber; }
}
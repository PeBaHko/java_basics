package org.example;

import java.util.Random;

import static java.lang.System.out;

public class User implements Runnable {
    public static volatile boolean exit = false;
    private final Account account;
    private final Bank bank;
    private final Random random = new Random();

    public User(Account account, Bank bank) {
        this.account = account;
        this.bank = bank;
    }

    @Override
    public void run() {
        int action;
        long balance;
        String dstAccount;
        long sum;
        while (!exit) {
            action = random.nextInt(3000) + 1;
            if (account.getStatus()) {
                out.println("Счёт № " + account.getAccNumber() + " заблокирован");
            } else {
                if (action % 3 == 0) { balance = bank.getBalance(account.getAccNumber()); }
                if (action % 10 == 0) {
                    dstAccount = bank.getRandomAccount();
                    try {
                        sum = bank.getSumAllAccounts();
                        bank.transfer(account.getAccNumber(), dstAccount, getAmount());
                        out.println("дельта до и после транзакции - " + (sum - bank.getSumAllAccounts()));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private long getAmount() {
        int percent = random.nextInt(1000) + 1;
        long amount;
        if (percent % 20 == 0) { amount = 100000;
        } else { amount = 10000; }
        synchronized (account) {
            if (account.getMoney() > 0) {
                return amount;
            } else return 0;
        }
    }
}
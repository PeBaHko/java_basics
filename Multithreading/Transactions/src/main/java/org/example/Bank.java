package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Bank {

    public Bank() { }

    private final Map<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        Account dstAccount = accounts.get(toAccountNum);

        synchronized (accounts) {
            Account srcAccount = accounts.get(fromAccountNum);
            if (srcAccount.getMoney() >= amount) {
                srcAccount.subMoney(amount);
                dstAccount.addMoney(amount);
            }
        }
        if (amount > 50000) {
            if (isFraud(fromAccountNum, toAccountNum, amount)) {
               synchronized (accounts) {
                   accounts.get(fromAccountNum).Blocked();
                   accounts.get(toAccountNum).Blocked();
               }
            }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        synchronized (accounts) { return accounts.get(accountNum).getMoney(); }
    }

    public synchronized String addAccount(long amount) {
        int id;
        do { id = random.nextInt(1000000) + 1; } while (accounts.containsKey(String.valueOf(id)));
        accounts.put(String.valueOf(id), new Account(amount, String.valueOf(id)));
        return String.valueOf(id);
    }

    public Account getAccount (String key) {
        return accounts.get(key);
    }

    public long getSumAllAccounts() {
        Set<String> keys = accounts.keySet();
        Account account;
        long sum = 0;
        synchronized (accounts) {
            for (String key : keys) {
                account = accounts.get(key);
                sum += account.getMoney();
            }
        }
        return sum;
    }

    public synchronized String getRandomAccount() {
        int size = accounts.size();
        int number = random.nextInt(size);
        Set<String> keys = accounts.keySet();
        int count = 0;
        String account = "";
        for (String key : keys) {
            if (count == number) { account = key; }
            count++;
        }
        return account;
    }
}
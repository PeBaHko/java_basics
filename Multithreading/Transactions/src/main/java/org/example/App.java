package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Account account;
        List<User> users = new ArrayList<>();
        long amount = 1000000;
        for (int i = 0; i < 1000; i++) {
            account = bank.getAccount(bank.addAccount(amount));
            //user = ;
            users.add(new User(account, bank));
        }
        for (User user : users) {
            new Thread(user).start();
        }
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        User.exit = true;
    }
}
package org.example;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import static java.lang.System.out;

public class App {
    private static final int SLEEP = 1000;
    private static final int USERS_COUNT = 20;
    public static void main( String[] args ){
        RedisSite redis = new RedisSite();
        redis.init();
        redis.createDB(USERS_COUNT);
        Iterator iterator;
        out.println("запускаем цикл для перебора пользователей");
        Set<String> set;
        while (true) {
            iterator = redis.getIterator();
            out.println("новый обход");
            set = new HashSet<>();
        //       for(String user_id : redis.getUsers()) {
               while (iterator.hasNext()) {
                try {
                    String user_id = (String) iterator.next();
                    if(!set.contains(user_id)) {
                        out.println(" -- На главной странице показываем пользователя "+ user_id);
                        Thread.sleep(SLEEP);
                    }
                    int ver = new Random().nextInt(1000) + 1;
                    int v = ver % 10;
                    if (v == 0) {
                        int id = new Random().nextInt(USERS_COUNT) +1;
                        out.println(" > пользователь " + id + " оплатил услугу продвижения");
                        out.println(" -- На главной странице показываем пользователя "+ id);
                        set.add(String.valueOf(id));
                        Thread.sleep(SLEEP);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        //redis.shutdown();
    }
}
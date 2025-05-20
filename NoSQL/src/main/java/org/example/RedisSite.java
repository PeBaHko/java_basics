package org.example;

import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Date;
import java.util.Iterator;

import static java.lang.System.out;

public class RedisSite {
    private RedissonClient redisson;
    private RKeys rKeys;
    private final static String KEY = "USERS";
    private RScoredSortedSet<String> users;
    public RedisSite() {}

    public RScoredSortedSet<String> getUsers() {
        return users;
    }
    public void addUser(int user_id) {
        users.add(getTs(), String.valueOf(user_id));
    }
    private double getTs() {
        return (double) (new Date().getTime() / 10);
    }
    public void createDB(int count) {
        out.println("создание новой БД");
        for (int i = 1; i <= count; i++) {
            addUser(i);try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public Iterator getIterator() {
        return users.iterator();
    }
    public void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException e) {
            out.println(e.getMessage());
        }
        rKeys = redisson.getKeys();
        users = redisson.getScoredSortedSet(KEY);
        rKeys.delete(KEY);
    }
    public void shutdown() {
        redisson.shutdown();
    }
}
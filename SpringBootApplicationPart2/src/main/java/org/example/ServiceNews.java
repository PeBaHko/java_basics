package org.example;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ServiceNews implements DefaultService<News> {

    private final ConcurrentHashMap<Long,News> newsMap;

    public ServiceNews() {
        newsMap = new ConcurrentHashMap<>();
    }

    public News getById(long id) {
        return newsMap.getOrDefault(id, null);
    }

    public Collection<News> getAll() {
        return newsMap.values();
    }

    public News create(News item) {
        long newKey = getLastKey() + 1;
        newsMap.put(newKey, new News(newKey, item.getTitle(), item.getText(), Instant.now()));
        return newsMap.get(newKey);
    }

    public News update(News item) {
        newsMap.replace(item.getId(), new News(item.getId(), item.getTitle(), item.getText(), Instant.now()));
        return newsMap.get(item.getId());
    }

    public boolean deleteById(long id) {
        if(newsMap.containsKey(id)) {
            newsMap.remove(id);
            return true;
        } else {
            return false;
        }
    }

    private long getLastKey() {
        Enumeration<Long> enumeration = newsMap.keys();
        long last = 0L;
        while (enumeration.hasMoreElements()) {
            last = enumeration.nextElement();
        }
        return last;
    }
}
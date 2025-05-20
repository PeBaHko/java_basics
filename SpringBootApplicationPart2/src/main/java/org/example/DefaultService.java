package org.example;

import java.util.Collection;

public interface DefaultService<T> {
    T getById(long id);
    Collection<T> getAll();
    T create(T item);
    T update(T item);
    boolean deleteById(long id);
}

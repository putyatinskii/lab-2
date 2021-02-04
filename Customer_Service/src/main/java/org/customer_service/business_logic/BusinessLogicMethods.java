package org.customer_service.business_logic;

import java.util.List;

public interface BusinessLogicMethods<T> {
    List<T> getAll();
    T get(Integer id);
    T create(T t);
    T update(Integer id, T t);
    void delete(T t);
}

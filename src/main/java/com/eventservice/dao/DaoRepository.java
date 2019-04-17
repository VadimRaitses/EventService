package com.eventservice.dao;

import java.util.Collection;
import java.util.List;


/**
 * @author Raitses Vadim
 */

public interface DaoRepository<T> {


    List<T> getByIdSortedByTime(String id, Class entityClass);

    T save(T t, String collectionName);

}

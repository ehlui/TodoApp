package com.ehlui.repository;

import java.util.List;

/**
 *  <h3>Business Layer</h3>
 *
 *  <p>A repository interface to define our CRUD operations</p>
 * @param <T>
 */
public interface CrudRepository<T> {

    T find(int id);

    List<T> findAll();

    void create(T t);

    void delete(int id);

    void update(T t);

}

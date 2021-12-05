package com.ehlui.dao;

import java.util.List;

/**
 * CRUD operations for a dao class
 *
 * @param <T> The actual type
 */
public interface Dao<T> {
    /**
     * Search for an entity in a DB from its ID
     *
     * @param id its id
     * @return the actual object parsed from the DB
     */
    T find(int id);

    /**
     * Gets all entities from a table in a DB
     *
     * @return A list of all entities
     */
    List<T> find();

    /**
     * Removes an entity by its id
     *
     * @return if it was removed
     */
    boolean remove(int id);

    /**
     * Adds a new entity
     *
     * @param t The actual object
     * @return If it was added
     */
    boolean add(T t);

    /**
     * It updates an entity (fully update but if a field is null it won't)
     *
     * @param t the object's ID
     * @return if it was updated successfully
     */
    boolean update(T t);

}

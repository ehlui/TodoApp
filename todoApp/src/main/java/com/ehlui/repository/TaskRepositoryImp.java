package com.ehlui.repository;

import com.ehlui.dao.Dao;
import com.ehlui.model.Task;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * Task {@link com.ehlui.model.Task} repository class
 *
 * <p>Dependencies:</p>
 * <ul>
 *     <li> myConnectionPool {@link javax.sql.DataSource} </li>
 *     <li> taskDaoImp {@link com.ehlui.dao.TaskDaoImp} </li>
 * </ul>
 */
public class TaskRepositoryImp implements Repository<Task> {
    @Resource(name = "jdbc/todoappDB")
    private DataSource myConnectionPool;

    private final Dao<Task> taskDaoImp;

    public TaskRepositoryImp(Dao<Task> daoTask) {
        if (daoTask == null)
            throw new NullPointerException("DataSource dependency in TaskRepositoryImp");
        this.taskDaoImp = daoTask;
    }

    @Override
    public Task find(int id) {
        return taskDaoImp.find(id);
    }

    @Override
    public List<Task> findAll() {
        return taskDaoImp.find();
    }

    @Override
    public void create(Task task) {
        taskDaoImp.add(task);
    }

    @Override
    public void delete(int id) {
        taskDaoImp.remove(id);
    }

    @Override
    public void update(Task task) {
        taskDaoImp.update(task);
    }
}

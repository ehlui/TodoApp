package com.ehlui.dao;

import com.ehlui.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h3>Persistence layer</h3>
 *
 * <p>Task {@link com.ehlui.model.Task} DAO class for making CRUD operations.</p>
 *
 * <p>Dependencies:</p>
 * <ul>
 *     <li> connection {@link java.sql.Connection} </li>
 * </ul>
 */
public class TaskDaoImp implements Dao<Task> {

    private final Connection connection;

    public TaskDaoImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Task find(int id) {
        if (id < 1)
            return null;

        Task task = null;
        final String query = "SELECT * FROM Task where id = ?;";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            if (rs.isLast())
                task = new Task(rs.getString(1), rs.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public List<Task> find() {
        List<Task> tasks = null;
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Task; ")) {
            tasks = new ArrayList<>();
            while (rs.next())
                tasks.add(new Task(rs.getString(2), rs.getString(3)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public boolean remove(int id) {
        if (id < 1)
            return false;

        final String query = "DELETE FROM Task WHERE id = ?;";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected == 1)
                return true;
            System.out.println("remove: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean add(Task task) {
        if (task == null)
            return false;

        final String preparedStmt = "INSERT INTO Task (name,description) VALUES (?,?);";
        try (PreparedStatement pst = this.connection.prepareStatement(preparedStmt)) {
            pst.setString(1, task.getName());
            pst.setString(2, task.getDescription());
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected == 1)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Task task) {
        if (task == null)
            return false;

        final String preparedStmt = "UPDATE Task SET name = ?, description = ? WHERE id = ?;";
        try (PreparedStatement pst = this.connection.prepareStatement(preparedStmt)) {
            pst.setString(1, task.getName());
            pst.setString(2, task.getDescription());
            pst.setInt(3, task.getId());
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected == 1)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

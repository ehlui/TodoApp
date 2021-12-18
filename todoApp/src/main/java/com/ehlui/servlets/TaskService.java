package com.ehlui.servlets;

import com.ehlui.dao.Dao;
import com.ehlui.dao.TaskDaoImp;
import com.ehlui.model.Task;
import com.ehlui.repository.Repository;
import com.ehlui.repository.TaskRepositoryImp;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(description = "Add a new task controller", urlPatterns = {"/AddTask"})
public class AddTask extends HttpServlet {

    @Resource(name = "jdbc/todoappDB")
    private DataSource myConnectionPool;

    private Repository<Task> taskRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        injectConnectionToRepository();

    }

    /**
     * It makes the dependency injection directly to its dependencies
     *
     * @throws SQLException
     */
    private void injectConnectionToRepository() {
        try {
            Dao<Task> daoTask = new TaskDaoImp(myConnectionPool.getConnection());
            this.taskRepository = new TaskRepositoryImp(daoTask);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO: Add a Servlet Filter for "Name" field (if its empty make some warning)
        String name = req.getParameter("task-name");
        String desc = req.getParameter("task-description");
        Task task = new Task(name, desc);
        this.taskRepository.create(task);
        req.setAttribute("taskList", taskRepository.findAll());

        req.getSession().setAttribute("taskList", taskRepository.findAll());
        resp.sendRedirect("tasks-list.jsp");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.taskRepository.delete(13);
        resp.setStatus(200);
    }
}


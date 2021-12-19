package com.ehlui.servlets;

import com.ehlui.dao.Dao;
import com.ehlui.dao.TaskDaoImp;
import com.ehlui.model.Task;
import com.ehlui.repository.Repository;
import com.ehlui.repository.TaskRepositoryImp;
import com.google.gson.Gson;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@WebServlet(description = "Add a new task controller", urlPatterns = {"/TaskService/*"})
public class TaskService extends HttpServlet {

    @Resource(name = "jdbc/todoappDB")
    private DataSource myConnectionPool;

    private Repository<Task> taskRepository;
    private Gson gson = new Gson();

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Task> allTasks = this.taskRepository.findAll();
        String employeeJsonString = this.gson.toJson(allTasks);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        out.flush();
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = inputStreamToString(req.getInputStream());
        // TODO: Use the id from the body and update (if it's not null) the tasks to update
        Task taskToUpdate = this.gson.fromJson(body,Task.class);
        this.taskRepository.update(taskToUpdate);
        doGet(req,resp);
    }

    // TODO: Make a doPatch for updating partially a Task (a field as name only for instance)

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = retrieveUserid(req);
        this.taskRepository.delete(userId);
        resp.setStatus(200);
    }

    private static int retrieveUserid(HttpServletRequest req) {
        String pathInfo = req.getPathInfo();
        if (pathInfo.startsWith("/"))
            pathInfo = pathInfo.substring(1);
        return Integer.parseInt(pathInfo);
    }

    private static String inputStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        return scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
    }
}


package com.ehlui.servlets;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistroUsuario
 */
@WebServlet(description = "practising with servlets", urlPatterns = { "/TodoTask" })
public class TodoTask extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoTask() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Try to think in a list with priority (hash_map/dictionary)
        String tasks[]={"Clean floor", "Wash dishes","go shopping","new"};
        // Adding the attribute for the response context (for the dispatched file)
        request.setAttribute("tasks", tasks);
        request.setAttribute("pepe", "jeje");
        // Create an instance of dispatcher passing the path of the view to be dispatched by this servlet
        RequestDispatcher requestDistatcher = request.getRequestDispatcher("tasks.jsp");

        // Performing the reedirection
        requestDistatcher.forward(request, response);
    }

}
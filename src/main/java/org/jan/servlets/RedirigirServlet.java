package org.jan.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/redirigir")
public class RedirigirServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.setHeader("Location",req.getContextPath() + "/students.html");
        //resp.setStatus(HttpServletResponse.SC_FOUND);
        resp.sendRedirect(req.getContextPath() + "/students.html");

    }
}

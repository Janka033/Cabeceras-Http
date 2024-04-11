package org.jan.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jan.mapping.dto.StudentDto;
import org.jan.service.StudentService;
import org.jan.service.impl.StudentServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;


@WebServlet("/buscar-students")
public class BuscarStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StudentService service = new StudentServiceImpl();
        List<StudentDto> studentDto = service.listar();

        String nombre = req.getParameter("student");
        Optional<StudentDto> encontrado = studentDto.stream().filter(s -> {
           if (nombre == null || nombre.isBlank()){
               return false;
           }
            return s.name().contains(nombre);
        }).findFirst();
        if (encontrado.isPresent()){
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\"");
                out.println("        <title>Estudiante Encontrado</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("     <h1>Estudiante encontrado</h1>");
                out.println("     <h3>Estudiante encontrado. " + "<br>Id:" + encontrado.get().id()
                        + "<br>Nombre: " + encontrado.get().name()
                        + "<br>Email: " + encontrado.get().email()
                        + "<br>Semestre: " + encontrado.get().semestre() + "</h3>");

                out.println("    </body>");
                out.println("</html");
            }

        }else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,"Lo sentimos, no se encontró el nombre " + nombre);
        }
    }
}

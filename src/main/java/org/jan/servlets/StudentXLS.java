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

// Esta anotación indica que esta clase es un servlet y especifica los patrones de URL que activarán este servlet.
@WebServlet({"/students.xls","/students.html","/students"})
public class StudentXLS extends HttpServlet{

    @Override// Método que maneja las solicitudes GET.
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        // Aquí hacemos una instancia de un servicio que proporciona la lista de estudiantes.
        StudentService service = new StudentServiceImpl();
        List<StudentDto> studentDtos = service.listar();
        // Establece el tipo de contenido de la respuesta a texto/html con codificación UTF-8.
        resp.setContentType("text/html;charset=UTF-8");
        // Obtiene el camino del servlet de la solicitud actual.
        String servletPath = req.getServletPath();
        // Comprueba si la solicitud actual termina con ".xls".
        boolean esXls = servletPath.endsWith(".xls");
        // Si es una solicitud .xls, establece el tipo de contenido y el encabezado de la respuesta para descargar un archivo de Excel.
        if (esXls) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment;filename=students.xls");
        }
        // Abre un PrintWriter para escribir la respuesta.
        try (PrintWriter out = resp.getWriter()) {
            if (!esXls) {
                // Si no es una solicitud .xls, genera una página HTML con la lista de estudiantes.
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Listado de Estudiantes</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Listado de Estudiantes!</h1>");
                out.println("<p><a href=\"" + req.getContextPath()
                        + "/students.xls" + "\">exportar a xls</a></p>");
                out.println("<p><a href=\"" + req.getContextPath()
                        + "/students.json" + "\">mostrar json</a></p>");
            }
            // Genera una tabla HTML con los detalles de los estudiantes.
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>id</th>");
            out.println("<th>nombre</th>");
            out.println("<th>tipo</th>");
            out.println("<th>precio</th>");
            out.println("</tr>");
            // Itera sobre la lista de estudiantes y genera una fila HTML para cada uno.
            studentDtos.forEach(p -> {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getName() + "</td>");
                out.println("<td>" + p.getEmail() + "</td>");
                out.println("<td>" + p.getSemestre() + "</td>");
                out.println("</tr>");
            });
            // Cierra la tabla.
            out.println("</table>");
            // Si no es una solicitud .xls, cierra la página HTML.
            if (!esXls) {
                out.println(" </body>");
            }
        }
    }
    }
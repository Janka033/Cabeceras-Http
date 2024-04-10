package org.jan.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;// Importamos la clase LocalTime de java.time para manejar la hora actual
import java.time.format.DateTimeFormatter;// Importamos DateTimeFormatter de java.time.format para formatear la hora

@WebServlet("/hora-actualizada")
public class HoraActualizadaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");// Establecemos el tipo de contenido de la respuesta como texto HTML con codificación UTF-8
        resp.setHeader("refresh","1");// Configuramos la cabecera HTTP refresh para que el navegador actualice la página cada 1 segundo
        LocalTime hora = LocalTime.now();// Obtenemos la hora actual del sistema
        DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mm:ss");// Creamos un formateador de hora para formatear la hora en formato hh:mm:ss
        try (PrintWriter out = resp.getWriter()) {// Abre un flujo de escritura hacia el navegador para escribir la respuesta HTML
            out.println("<!DOCTYPE html>");// Escribimos el doctype HTML
            out.println("<html");// Inicia el elemento html
            out.println("    <head>");// Inicia la sección head(cabecera)
            out.println("        <meta charset=\"UTF-8\"");// Establece la codificación del documento como UTF-8
            out.println("        <title>La hora Actualizada</title>");// Establece el título de la página
            out.println("    </head>");// Cierra la sección head
            out.println("    <body>");// Inicia la sección body
            out.println("     <h1>La hora Actualizada</h1>"); // Escribe un encabezado h1
            out.println("<h3>" + hora.format(df) + "</h3>");// Escribe la hora actual formateada en un encabezado h3
            out.println("    </body>");// Cierra la sección body
            out.println("</html"); // Cierra el elemento html
        }

    }
}

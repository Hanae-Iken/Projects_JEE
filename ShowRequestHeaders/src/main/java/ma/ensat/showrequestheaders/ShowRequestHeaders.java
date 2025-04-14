package com.exemple;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/show-headers")
public class ShowRequestHeaders extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String title = "En-têtes HTTP de la requête";
        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>" + title + "</title></head>");
        out.println("<body><h1>" + title + "</h1>");
        out.println("<b>Méthode de requête :</b> " + request.getMethod() + "<br>");
        out.println("<b>URI de requête :</b> " + request.getRequestURI() + "<br>");
        out.println("<b>Protocole :</b> " + request.getProtocol() + "<br>");

        out.println("<table border='1'><tr><th>Nom de l'en-tête</th><th>Valeur</th></tr>");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            out.println("<tr><td>" + headerName + "</td>");
            out.println("<td>" + request.getHeader(headerName) + "</td></tr>");
        }
        out.println("</table></body></html>");
    }
}
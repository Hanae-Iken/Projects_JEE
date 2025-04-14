package ma.ensat.formservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/show-params")
public class FormServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String title = "Paramètres reçus";
        String doctype = "<!DOCTYPE html>";

        out.println(doctype +
                "<html>\n<head><title>" + title + "</title></head>\n" +
                "<body>\n<h1>" + title + "</h1>\n" +
                "<table border=\"1\">\n" +
                "<tr><th>Nom Paramètre</th><th>Valeur(s)</th></tr>");

        Enumeration<String> paramNames = request.getParameterNames();

        while(paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            out.println("<tr><td>" + paramName + "</td>");
            String[] paramValues = request.getParameterValues(paramName);
            out.println("<td>");
            for(int i=0; i<paramValues.length; i++) {
                if(i>0) out.println(", ");
                out.println(paramValues[i]);
            }
            out.println("</td></tr>");
        }
        out.println("</table>\n</body></html>");
    }

    // Pour permettre aussi l'accès en GET (comme dans le PPT)
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response); // Réutilise la même logique
    }
}
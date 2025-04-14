package ma.ensat.formservlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/process-form")
public class FormServlet extends HttpServlet {

    // Méthode GET (pour les accès directs)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Accès Direct</title></head>");
        out.println("<body><h1>Utilisez le formulaire SVP</h1>");
        out.println("<a href='formulaire.html'>Retour au formulaire</a>");
        out.println("</body></html>");
    }

    // Méthode POST (pour le traitement du formulaire)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Récupération des paramètres
        String nom = request.getParameter("nom");
        String age = request.getParameter("age");
        String email = request.getParameter("email");

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Résultat</title></head>");
        out.println("<body><h1>Données reçues (POST) :</h1>");
        out.println("<ul>");
        out.println("<li>Nom : " + escapeHtml(nom) + "</li>");
        out.println("<li>Âge : " + escapeHtml(age) + "</li>");
        out.println("<li>Email : " + escapeHtml(email) + "</li>");
        out.println("</ul>");
        out.println("</body></html>");
    }

    // Méthode de sécurité pour éviter les injections XSS
    private String escapeHtml(String input) {
        if (input == null) return "Non renseigné";
        return input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}
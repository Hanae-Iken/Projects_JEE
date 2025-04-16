package ma.ensat.projet_messagerie.servelets;

import ma.ensat.projet_messagerie.beans.Message;
import ma.ensat.projet_messagerie.beans.Personne;
import ma.ensat.projet_messagerie.dao.DAOServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/connexion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String motDePasse = request.getParameter("motDePasse");

        Personne personne = DAOServices.authenticateUser(nom, prenom, motDePasse);

        if (personne != null) {
            HttpSession session = request.getSession();
            session.setAttribute("utilisateur", personne);

            // Récupérer les messages de l'utilisateur
            List<Message> messages = DAOServices.getUserMessages(personne.getIdPersonne());
            request.setAttribute("messages", messages);

            response.sendRedirect(request.getContextPath() + "/compte");
        } else {
            request.setAttribute("erreur", "Identifiants incorrects. Veuillez réessayer.");
            request.getRequestDispatcher("/connexion.jsp").forward(request, response);
        }
    }
}
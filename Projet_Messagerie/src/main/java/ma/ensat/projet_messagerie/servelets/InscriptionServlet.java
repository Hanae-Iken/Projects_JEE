package ma.ensat.projet_messagerie.servelets;

import ma.ensat.projet_messagerie.dao.DAOServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/inscription.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String motDePasse = request.getParameter("motDePasse");

        boolean inscriptionReussie = DAOServices.registerUser(nom, prenom, motDePasse);

        if (inscriptionReussie) {
            request.setAttribute("message", "Inscription réussie ! Vous pouvez maintenant vous connecter.");
            request.getRequestDispatcher("/connexion.jsp").forward(request, response);
        } else {
            request.setAttribute("erreur", "Erreur lors de l'inscription. Veuillez réessayer.");
            request.getRequestDispatcher("/inscription.jsp").forward(request, response);
        }
    }
}
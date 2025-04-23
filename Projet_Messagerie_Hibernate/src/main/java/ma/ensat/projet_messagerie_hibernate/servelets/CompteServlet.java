package ma.ensat.projet_messagerie_hibernate.servelets;

import ma.ensat.projet_messagerie_hibernate.beans.Message;
import ma.ensat.projet_messagerie_hibernate.beans.Personne;
import ma.ensat.projet_messagerie_hibernate.dao.DAOServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/compte")
public class CompteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("utilisateur") != null) {
            Personne utilisateur = (Personne) session.getAttribute("utilisateur");
            List<Message> messages = DAOServices.getUserMessages(utilisateur.getIdPersonne());
            request.setAttribute("messages", messages);
            request.getRequestDispatcher("/compte.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/connexion");
        }
    }
}
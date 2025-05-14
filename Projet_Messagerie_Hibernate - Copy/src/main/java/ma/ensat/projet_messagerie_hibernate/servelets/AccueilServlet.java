package ma.ensat.projet_messagerie_hibernate.servelets;

import ma.ensat.projet_messagerie_hibernate.beans.Message;
import ma.ensat.projet_messagerie_hibernate.dao.DAOServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Message> messages = DAOServices.getPublicMessages();
        request.setAttribute("messages", messages);
        request.getRequestDispatcher("/accueil.jsp").forward(request, response);
    }
}
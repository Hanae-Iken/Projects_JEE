package ma.ensat.projet_messagerie.servelets;

import ma.ensat.projet_messagerie.dao.DAOServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/sujets")
public class SujetsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> sujets = DAOServices.getPublicSubjects();
        request.setAttribute("sujets", sujets);
        request.getRequestDispatcher("/sujets.jsp").forward(request, response);
    }
}
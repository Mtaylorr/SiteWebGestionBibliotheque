package com.ensta.librarymanager.servlet;

import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.EmpruntServiceImpl;
import com.ensta.librarymanager.service.LivreServiceImpl;
import com.ensta.librarymanager.service.MembreServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/livre_details")
public class LivreDetailsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmpruntServiceImpl empruntServiceImpl = EmpruntServiceImpl.getInstance();
        MembreServiceImpl membreServiceImpl = MembreServiceImpl.getInstance();
        LivreServiceImpl livreServiceImpl = LivreServiceImpl.getInstance();
        int id = Integer.parseInt(request.getParameter("id"));
        List<Emprunt> empruntParLivres =null;
        try{
            empruntParLivres = empruntServiceImpl.getListCurrentByLivre(id);
            Livre livre = livreServiceImpl.getById(id);
            request.setAttribute("empruntParLivres", empruntParLivres);
            request.setAttribute("livre", livre);
            request.getRequestDispatcher("/WEB-INF/View/livre_details.jsp").forward(request, response);
        }catch(Exception e){
            throw new ServletException();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try{
            LivreServiceImpl livreServiceImpl = LivreServiceImpl.getInstance();
            String titre = request.getParameter("titre");
            String auteur = request.getParameter("auteur");
            String isbn = request.getParameter("isbn");
            Livre livre = new Livre(id, titre, auteur, isbn);
            livreServiceImpl.update(livre);
            response.sendRedirect("livre_details?id="+Integer.toString(id));
        }catch(Exception e){
            throw new ServletException();
        }

    }
}

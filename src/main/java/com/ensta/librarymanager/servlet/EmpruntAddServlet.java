package com.ensta.librarymanager.servlet;

import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.EmpruntServiceImpl;
import com.ensta.librarymanager.service.LivreServiceImpl;
import com.ensta.librarymanager.service.MembreServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/emprunt_add")
public class EmpruntAddServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmpruntServiceImpl empruntServiceImpl = EmpruntServiceImpl.getInstance();
        MembreServiceImpl membreServiceImpl = MembreServiceImpl.getInstance();
        LivreServiceImpl livreServiceImpl = LivreServiceImpl.getInstance();
        List<Livre> livreDispo = null;
        List<Membre> membreDispo = null;
        try{
            livreDispo = livreServiceImpl.getListDispo();
            membreDispo = membreServiceImpl.getListMembreEmpruntPossible();
            request.setAttribute("livreDispo", livreDispo);
            request.setAttribute("membreDispo",membreDispo);
            request.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp").forward(request, response);
        }catch(Exception e){
            throw new ServletException();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idLivre = Integer.parseInt(request.getParameter("idLivre"));
        int idMembre = Integer.parseInt(request.getParameter("idMembre"));
        try{
            EmpruntServiceImpl empruntServiceImpl = EmpruntServiceImpl.getInstance();
            empruntServiceImpl.create(idMembre,idLivre, LocalDate.now());
            response.sendRedirect("emprunt_list");
        }catch(Exception e){
            throw new ServletException();
        }

    }
}

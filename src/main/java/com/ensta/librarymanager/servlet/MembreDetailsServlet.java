package com.ensta.librarymanager.servlet;

import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.EmpruntServiceImpl;
import com.ensta.librarymanager.service.LivreServiceImpl;
import com.ensta.librarymanager.service.MembreServiceImpl;
import org.graalvm.compiler.debug.CSVUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/membre_details")
public class MembreDetailsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmpruntServiceImpl empruntServiceImpl = EmpruntServiceImpl.getInstance();
        MembreServiceImpl membreServiceImpl = MembreServiceImpl.getInstance();
        LivreServiceImpl livreServiceImpl = LivreServiceImpl.getInstance();
        int id = Integer.parseInt(request.getParameter("id"));
        List<Emprunt> emprunts =null;

        try{
            emprunts = empruntServiceImpl.getListCurrentByMembre(id);
            System.out.println();
            Membre membre = membreServiceImpl.getById(id);
            request.setAttribute("emprunts", emprunts);
            request.setAttribute("membre", membre);
            request.getRequestDispatcher("/WEB-INF/View/membre_details.jsp").forward(request, response);
        }catch(Exception e){
            throw new ServletException();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try{
            MembreServiceImpl membreServiceImpl = MembreServiceImpl.getInstance();
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String adresse = request.getParameter("adresse");
            String email = request.getParameter("email");
            String telephone = request.getParameter("telephone");
            String abonnements = request.getParameter("abonnement");
            Membre.Abonnement abonnement = Membre.Abonnement.valueOf(abonnements);
            Membre membre = new Membre(id, nom, prenom, adresse, email, telephone,abonnement);
            membreServiceImpl.update(membre);
            response.sendRedirect("membre_details?id="+Integer.toString(id));
        }catch(Exception e){
            throw new ServletException();
        }

    }
}

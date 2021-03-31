package com.ensta.librarymanager.servlet;

import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.LivreServiceImpl;
import com.ensta.librarymanager.service.MembreServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/membre_add")
public class MembreAddServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            request.getRequestDispatcher("/WEB-INF/View/membre_add.jsp").forward(request, response);
        }catch(Exception e){
            throw new ServletException();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String adresse = request.getParameter("adresse");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        try{
            MembreServiceImpl membreServiceImpl = MembreServiceImpl.getInstance();
            int nvid = membreServiceImpl.create(nom,prenom,adresse,email,telephone);
            String redirectionLink = "membre_details?id="+Integer.toString(nvid);
            response.sendRedirect(redirectionLink);
        }catch(Exception e){
            throw new ServletException();
        }

    }
}

package com.ensta.librarymanager.servlet;

import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
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


@WebServlet("/livre_delete")
public class LivreDeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmpruntServiceImpl empruntServiceImpl = EmpruntServiceImpl.getInstance();
        MembreServiceImpl membreServiceImpl = MembreServiceImpl.getInstance();
        LivreServiceImpl livreServiceImpl = LivreServiceImpl.getInstance();
        int id = Integer.parseInt(request.getParameter("id"));
        try{
            Livre livre = livreServiceImpl.getById(id);
            request.setAttribute("livre", livre);
            request.getRequestDispatcher("/WEB-INF/View/livre_delete.jsp").forward(request, response);
        }catch(Exception e){
            throw new ServletException();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try{
            LivreServiceImpl livreServiceImpl = LivreServiceImpl.getInstance();
            livreServiceImpl.delete(id);
            response.sendRedirect("livre_list");
        }catch(Exception e){
            throw new ServletException();
        }

    }
}

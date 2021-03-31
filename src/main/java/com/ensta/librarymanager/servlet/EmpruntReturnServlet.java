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
import java.time.LocalDate;
import java.util.List;

@WebServlet("/emprunt_return")
public class EmpruntReturnServlet  extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmpruntServiceImpl empruntServiceImpl = EmpruntServiceImpl.getInstance();
        MembreServiceImpl membreServiceImpl = MembreServiceImpl.getInstance();
        LivreServiceImpl livreServiceImpl = LivreServiceImpl.getInstance();
        List<Emprunt> empruntsCourant = null;
        try{
            empruntsCourant = empruntServiceImpl.getListCurrent();
            request.setAttribute("empruntsCourant", empruntsCourant);
            request.getRequestDispatcher("/WEB-INF/View/emprunt_return.jsp").forward(request, response);
        }catch(Exception e){
            throw new ServletException();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try{
            EmpruntServiceImpl empruntServiceImpl = EmpruntServiceImpl.getInstance();
            //System.out.println(id);
            //System.out.println(empruntServiceImpl.getListCurrent());
            empruntServiceImpl.returnBook(id);
            response.sendRedirect("dashboard");
        }catch(Exception e){
            throw new ServletException();
        }

    }
}

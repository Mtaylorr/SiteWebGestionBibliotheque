package com.ensta.librarymanager.servlet;

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


@WebServlet("/membre_list")
public class MembreListServlet  extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmpruntServiceImpl empruntServiceImpl = EmpruntServiceImpl.getInstance();
        MembreServiceImpl membreServiceImpl = MembreServiceImpl.getInstance();
        LivreServiceImpl livreServiceImpl = LivreServiceImpl.getInstance();
        List<Membre> membres =null;
        try{
            membres = membreServiceImpl.getList();
            request.setAttribute("membres",membres);
            request.getRequestDispatcher("/WEB-INF/View/membre_list.jsp").forward(request, response);
        }catch(Exception e){
            throw new ServletException();
        }
    }
}

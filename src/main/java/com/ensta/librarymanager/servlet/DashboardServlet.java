package com.ensta.librarymanager.servlet;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.service.EmpruntServiceImpl;
import com.ensta.librarymanager.service.LivreServiceImpl;
import com.ensta.librarymanager.service.MembreServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmpruntServiceImpl empruntServiceImpl = EmpruntServiceImpl.getInstance();
        MembreServiceImpl membreServiceImpl = MembreServiceImpl.getInstance();
        LivreServiceImpl livreServiceImpl = LivreServiceImpl.getInstance();
        int nbDeMembres=0 , nbDeLivres=0,nbDeEmprunts=0;
        List<Emprunt> listDeEmpruntsCourant=null;
        try{
            nbDeMembres = membreServiceImpl.count();
            nbDeLivres = livreServiceImpl.count();
            nbDeEmprunts =  empruntServiceImpl.count();
            listDeEmpruntsCourant = empruntServiceImpl.getListCurrent();
        }catch(Exception e){
            throw new ServletException();
        }
        RequestDispatcher r =  request.getRequestDispatcher("/WEB-INF/View/dashboard.jsp");
        request.setAttribute("nbDeMembres",nbDeMembres);
        request.setAttribute("nbDeLivres",nbDeLivres);
        request.setAttribute("nbDeEmprunts",nbDeEmprunts);
        request.setAttribute("listDeEmpruntsCourant",listDeEmpruntsCourant);
        r.forward(request, response);
    }
}

package com.ensta.librarymanager.servlet;


import com.ensta.librarymanager.modele.Emprunt;
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

@WebServlet("/emprunt_list")
public class  EmpruntListServlet extends HttpServlet {
    boolean cond = false;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmpruntServiceImpl empruntServiceImpl = EmpruntServiceImpl.getInstance();
        MembreServiceImpl membreServiceImpl = MembreServiceImpl.getInstance();
        LivreServiceImpl livreServiceImpl = LivreServiceImpl.getInstance();
        List<Emprunt> empruntsCournat =null;
        cond = (request.getParameter("show")!=null);
        try{
            if(!cond)
                empruntsCournat = empruntServiceImpl.getListCurrent();
            else
                empruntsCournat = empruntServiceImpl.getList();
            request.setAttribute("empruntsCournat",empruntsCournat);
            request.getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp").forward(request, response);
        }catch(Exception e){
            throw new ServletException();
        }
    }

}

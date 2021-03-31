package com.ensta.librarymanager.service;

import com.ensta.librarymanager.dao.EmpruntDaoImpl;
import com.ensta.librarymanager.dao.LivreDaoImpl;
import com.ensta.librarymanager.dao.MembreDaoImpl;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpruntServiceImpl implements EmpruntService{
    private static EmpruntServiceImpl instance;
    private EmpruntServiceImpl(){}
    public static EmpruntServiceImpl getInstance(){
        if(instance==null)
            instance = new EmpruntServiceImpl();
        return instance;
    }
    @Override
    public List<Emprunt> getList() throws ServiceException {
        List<Emprunt> res = null;
        try{
            EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
            res = empruntDaoImpl.getList();
        }catch(Exception e){
            throw new ServiceException();
        }
        return res;
    }

    @Override
    public List<Emprunt> getListCurrent() throws ServiceException {
        List<Emprunt> res =null;
        try{
            EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
            res = empruntDaoImpl.getListCurrent();
        }catch(Exception e){
            throw new ServiceException();
        }
        return res;
    }

    @Override
    public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException {
        List<Emprunt> res = null;
        try{
            EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
            res = empruntDaoImpl.getListCurrentByMembre(idMembre);
        }catch(Exception e){
            throw new ServiceException();
        }
        return res;
    }

    @Override
    public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException {
        List<Emprunt> res = null;
        try{
            EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
            res = empruntDaoImpl.getListCurrentByLivre(idLivre);
        }catch(Exception e){
            throw new ServiceException();
        }
        return res;
    }

    @Override
    public Emprunt getById(int id) throws ServiceException {
        Emprunt res = null;
        try{
            EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
            res = empruntDaoImpl.getById(id);
        }catch(Exception e){
            throw new ServiceException();
        }
        return res;
    }

    @Override
    public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException {
        Membre membre=null;
        Livre livre =null;
        try {
            MembreServiceImpl membreServiceImpl = MembreServiceImpl.getInstance();
            membre = membreServiceImpl.getById(idMembre);
            LivreServiceImpl livreServiceImpl = LivreServiceImpl.getInstance();
            livre = livreServiceImpl.getById(idLivre);

        }catch(Exception e){
            throw new ServiceException();
        }
        if((livre==null)|| (!isLivreDispo(idLivre)) || (membre==null) || (!isEmpruntPossible(membre))  )
            throw new ServiceException();
        try{
            EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
            empruntDaoImpl.create(idMembre,idLivre,dateEmprunt);
        }catch(Exception e){
            throw new ServiceException();
        }
    }

    @Override
    public void returnBook(int id) throws ServiceException {
        try{
            EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
            Emprunt emprunt = empruntDaoImpl.getById(id);
            emprunt.setDateRetour(LocalDate.now());
            System.out.println(emprunt);
            empruntDaoImpl.update(emprunt);

        }catch(Exception e){
            throw new ServiceException();
        }
    }

    @Override
    public int count() throws ServiceException {
        int res = 0;
        try{
            EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
            res = empruntDaoImpl.count();
        }catch(Exception e){
            throw new ServiceException();
        }
        return res;
    }

    @Override
    public boolean isLivreDispo(int idLivre) throws ServiceException {
        List<Emprunt> emprunts=null;
        try{
            EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
            emprunts = empruntDaoImpl.getListCurrentByLivre(idLivre);
        }catch(Exception e){
            throw new ServiceException();
        }
        return (emprunts==null || emprunts.size()==0);
    }

    @Override
    public boolean isEmpruntPossible(Membre membre) throws ServiceException {
        List<Emprunt> emprunts=null;
        Membre.Abonnement abonnement = membre.getAbonnement();
        int mx = (abonnement == Membre.Abonnement.BASIC ?2 : (abonnement== Membre.Abonnement.PREMIUM?5:20));

        try{
            EmpruntDaoImpl empruntDaoImpl = EmpruntDaoImpl.getInstance();
            emprunts = empruntDaoImpl.getListCurrentByMembre(membre.getId());
        }catch(Exception e){
            throw new ServiceException();
        }
        return (emprunts.size()<mx);
    }
}

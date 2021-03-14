package com.ensta.librarymanager.service;

import com.ensta.librarymanager.dao.MembreDaoImpl;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Membre;

import java.util.ArrayList;
import java.util.List;

public class MembreServiceImpl implements MembreService{
    private static MembreServiceImpl instance;
    private MembreServiceImpl(){}
    public static MembreServiceImpl getInstance(){
        if(instance==null)
            instance = new MembreServiceImpl();
        return instance;
    }

    @Override
    public List<Membre> getList() throws ServiceException {
        List<Membre>res = null;
        try {
            MembreDaoImpl membreDaoImpl = MembreDaoImpl.getInstance();
            res = membreDaoImpl.getList();
        }catch(Exception e){
            throw new ServiceException();
        }
        return res;
    }

    @Override
    public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
        List<Membre>res = new ArrayList<>();
        EmpruntServiceImpl  empruntServiceImpl =  EmpruntServiceImpl.getInstance();
        try {
            List<Membre> allmembers = getList();
            for(Membre membre :allmembers){
                if(empruntServiceImpl.isEmpruntPossible(membre)){
                    res.add(membre);
                }
            }
        }catch(Exception e){
            throw new ServiceException();
        }
        return res;
    }

    @Override
    public Membre getById(int id) throws ServiceException {
        Membre res = null;
        try {
            MembreDaoImpl membreDaoImpl = MembreDaoImpl.getInstance();
            res = membreDaoImpl.getById(id);
        }catch(Exception e){
            throw new ServiceException();
        }
        return res;
    }

    @Override
    public int create(String nom, String prenom, String adresse, String email, String telephone, Membre.Abonnement abonnement) throws ServiceException {
        if(nom.isEmpty() || prenom.isEmpty())
            throw new ServiceException();
        nom = nom.toUpperCase();
        int res=-1;
        try {
            MembreDaoImpl membreDaoImpl = MembreDaoImpl.getInstance();
            res = membreDaoImpl.create(nom,prenom, adresse, email , telephone, abonnement);
        }catch(Exception e){
            throw new ServiceException();
        }
        return res;
    }

    @Override
    public void update(Membre membre) throws ServiceException {
        if(membre.getNom().isEmpty() || membre.getPrenom().isEmpty())
            throw new ServiceException();
        try {
            MembreDaoImpl membreDaoImpl = MembreDaoImpl.getInstance();
            membreDaoImpl.update(membre);
        }catch(Exception e){
            throw new ServiceException();
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            MembreDaoImpl membreDaoImpl = MembreDaoImpl.getInstance();
            membreDaoImpl.delete(id);
        }catch(Exception e){
            throw new ServiceException();
        }
    }

    @Override
    public int count() throws ServiceException {
        int res=0;
        try {
            MembreDaoImpl membreDaoImpl = MembreDaoImpl.getInstance();
            res = membreDaoImpl.count();
        }catch(Exception e){
            throw new ServiceException();
        }
        return res;
    }
}

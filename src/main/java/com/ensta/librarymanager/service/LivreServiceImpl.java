package com.ensta.librarymanager.service;


import com.ensta.librarymanager.dao.LivreDaoImpl;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;

import java.util.ArrayList;
import java.util.List;

public class LivreServiceImpl implements LivreService {
    private static LivreServiceImpl instance;

    private LivreServiceImpl() {
    }

    public static LivreServiceImpl getInstance() {
        if (instance == null)
            instance = new LivreServiceImpl();
        return instance;
    }

    @Override
    public List<Livre> getList() throws ServiceException {
        List<Livre> res = new ArrayList<>();
        try {
            LivreDaoImpl livreDaoImpl = LivreDaoImpl.getInstance();
            res = livreDaoImpl.getList();
        } catch (Exception e) {
            throw new ServiceException();
        }
        return res;
    }

    @Override
    public List<Livre> getListDispo() throws ServiceException {
        List<Livre> res = new ArrayList<>();
        try {
            List<Livre> allLivres = getList();
            EmpruntServiceImpl empruntServiceImpl = EmpruntServiceImpl.getInstance();
            for (Livre livre : allLivres) {
                if (empruntServiceImpl.isLivreDispo(livre.getId()))
                    res.add(livre);
            }
        } catch (Exception e) {
            throw new ServiceException();
        }
        return res;
    }

    @Override
    public Livre getById(int id) throws ServiceException {
        Livre res = null;
        try {
            LivreDaoImpl livreDaoImpl = LivreDaoImpl.getInstance();
            res = livreDaoImpl.getById(id);
        } catch (Exception e) {
            throw new ServiceException();
        }
        return res;
    }

    @Override
    public int create(String titre, String auteur, String isbn) throws ServiceException {
        if (titre.isEmpty())
            throw new ServiceException();
        int res = -1;
        try {
            LivreDaoImpl livreDaoImpl = LivreDaoImpl.getInstance();
            res = livreDaoImpl.create(titre, auteur, isbn);
        } catch (Exception e) {
            throw new ServiceException();
        }
        return res;
    }

    @Override
    public void update(Livre livre) throws ServiceException {
        if (livre.getTitre().isEmpty())
            throw new ServiceException();
        try {
            LivreDaoImpl livreDaoImpl = LivreDaoImpl.getInstance();
            livreDaoImpl.update(livre);
        } catch (Exception e) {
            throw new ServiceException();
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            LivreDaoImpl livreDaoImpl = LivreDaoImpl.getInstance();
            livreDaoImpl.delete(id);
        } catch (Exception e) {
            throw new ServiceException();
        }
    }

    @Override
    public int count() throws ServiceException {
        int res=0;
        try {
            LivreDaoImpl livreDaoImpl = LivreDaoImpl.getInstance();
            res = livreDaoImpl.count();
        } catch (Exception e) {
            throw new ServiceException();
        }
        return res;
    }
}

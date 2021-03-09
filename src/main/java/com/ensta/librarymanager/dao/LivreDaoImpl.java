package com.ensta.librarymanager.dao;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.persistence.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDaoImpl implements  LivreDao {
    private static LivreDaoImpl instance;
    private LivreDaoImpl(){}
    public static LivreDaoImpl getInstance(){
        if(instance==null)
            instance = new LivreDaoImpl();
        return instance;
    }
    @Override
    public List<Livre> getList() throws DaoException {
        List<Livre> result = new ArrayList<>();
        String query = "SELECT id, titre, auteur, isbn FROM livre";
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                result.add(new Livre(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("isbn")));
            }
            statement.close();
        } catch (SQLException throwables) {
            throw new DaoException();
        }
        return result;
    }

    @Override
    public Livre getById(int id) throws DaoException {
        Livre result = null;
        String query = "SELECT id, titre, auteur, isbn FROM livre WHERE id = ?";
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                result = new Livre(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("isbn"));
            }
            statement.close();
        } catch (SQLException throwables) {
            throw new DaoException();
        }
        return result;
    }

    @Override
    public int create(String titre, String auteur, String isbn) throws DaoException {
        String query = "INSERT INTO livre(titre, auteur, isbn) VALUES (?, ?, ?)";
        Connection connection = null;
        int result=-1;
        try {
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,titre);
            statement.setString(2,auteur);
            statement.setString(3,isbn);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                result = rs.getInt(1);
            }
            statement.close();
        } catch (SQLException throwables) {
            throw new DaoException();
        }
        return result;
    }

    @Override
    public void update(Livre livre) throws DaoException {
        String query = "UPDATE livre SET titre = ?, auteur = ?, isbn = ? WHERE id = ?";
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,livre.getTitre());
            statement.setString(2,livre.getAuteur());
            statement.setString(3,livre.getIsbn());
            statement.setString(4,Integer.toString(livre.getId()));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException throwables) {
            throw new DaoException();
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        String query = "DELETE FROM livre WHERE id = ?";
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,Integer.toString(id));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException throwables) {
            throw new DaoException();
        }
    }

    @Override
    public int count() throws DaoException {
        int result = 0;
        String query = "SELECT COUNT(id) AS count FROM livre";
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                result = rs.getInt("count");
            }
            statement.close();
        } catch (SQLException throwables) {
            throw new DaoException();
        }
        return result;
    }
}

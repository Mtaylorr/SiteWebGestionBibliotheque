package com.ensta.librarymanager.dao;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.persistence.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembreDaoImpl  implements MembreDao{
    private static MembreDaoImpl instance;
    private MembreDaoImpl(){}
    public static MembreDaoImpl getInstance(){
        if(instance==null)
            instance = new MembreDaoImpl();
        return instance;
    }
    @Override
    public List<Membre> getList() throws DaoException {
        List<Membre> result = new ArrayList<>();
        String query = "SELECT id, nom, prenom, adresse, email, telephone, abonnement\n" +
                "FROM membre\n" +
                "ORDER BY nom, prenom";
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                result.add(new Membre(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        Membre.Abonnement.valueOf(rs.getString("abonnement"))));
            }
            statement.close();
        } catch (SQLException throwables) {
            throw new DaoException();
        }
        return result;
    }

    @Override
    public Membre getById(int id) throws DaoException {
        Membre result = null;
        String query = "SELECT id, nom, prenom, adresse, email, telephone, abonnement\n" +
                "FROM membre\n" +
                "WHERE id = ?";
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,Integer.toString(id));
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                result = new Membre(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        Membre.Abonnement.valueOf(rs.getString("abonnement")));
            }
            statement.close();
        } catch (SQLException throwables) {
            throw new DaoException();
        }
        return result;
    }

    @Override
    public int create(String nom, String prenom, String adresse, String email, String telephone, Membre.Abonnement abonnement) throws DaoException {
        String query = "INSERT INTO membre(nom, prenom, adresse, email, telephone,\n" +
                "abonnement)\n" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        int result=-1;
        try {
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,nom);
            statement.setString(2,prenom);
            statement.setString(3,adresse);
            statement.setString(4,email);
            statement.setString(5,telephone);
            statement.setString(6,abonnement.name().toString());
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
    public void update(Membre membre) throws DaoException {
        String query = "UPDATE membre\n" +
                "SET nom = ?, prenom = ?, adresse = ?, email = ?, telephone = ?,\n" +
                "abonnement = ?\n" +
                "WHERE id = ?";
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,membre.getNom());
            statement.setString(2,membre.getPrenom());
            statement.setString(3,membre.getAdresse());
            statement.setString(4,membre.getEmail());
            statement.setString(5,membre.getTelephone());
            statement.setString(6,membre.getAbonnement().name());
            statement.setString(7,Integer.toString(membre.getId()));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException throwables) {
            throw new DaoException();
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        String query = "DELETE FROM membre WHERE id = ?";
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
        String query = "SELECT COUNT(id) AS count FROM membre";
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

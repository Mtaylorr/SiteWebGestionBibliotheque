package com.ensta.librarymanager.dao;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.persistence.ConnectionManager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class EmpruntDaoImpl implements EmpruntDao {

    private static EmpruntDaoImpl instance;
    private EmpruntDaoImpl(){}
    public static EmpruntDaoImpl getInstance(){
        if(instance==null)
            instance = new EmpruntDaoImpl();
        return instance;
    }
    @Override
    public List<Emprunt> getList() throws DaoException {
        List<Emprunt> result = new ArrayList<>();
        String query = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,\n" +
                "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,\n" +
                "dateRetour\n" +
                "FROM emprunt AS e\n" +
                "INNER JOIN membre ON membre.id = e.idMembre\n" +
                "INNER JOIN livre ON livre.id = e.idLivre\n" +
                "ORDER BY dateRetour DESC";
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Membre membre = new Membre(
                        rs.getInt("idMembre"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        Membre.Abonnement.valueOf(rs.getString("abonnement")));
                Livre livre = new Livre(
                        rs.getInt("idLivre"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("isbn"));
                result.add(new Emprunt(
                        rs.getInt("id"),
                        membre,
                        livre,
                        rs.getDate("dateEmprunt").toLocalDate(),
                        rs.getDate("dateRetour")==null ? null : rs.getDate("dateRetour").toLocalDate()));
            }
            statement.close();
        } catch (SQLException throwables) {
            throw new DaoException();
        }
        return result;
    }

    @Override
    public List<Emprunt> getListCurrent() throws DaoException {
        List<Emprunt> result = new ArrayList<>();
        String query = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,\n" +
                "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,\n" +
                "dateRetour\n" +
                "FROM emprunt AS e\n" +
                "INNER JOIN membre ON membre.id = e.idMembre\n" +
                "INNER JOIN livre ON livre.id = e.idLivre\n" +
                "WHERE dateRetour IS NULL";
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Membre membre = new Membre(
                        rs.getInt("idMembre"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        Membre.Abonnement.valueOf(rs.getString("abonnement")));
                Livre livre = new Livre(
                        rs.getInt("idLivre"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("isbn"));
                result.add(new Emprunt(
                        rs.getInt("id"),
                        membre,
                        livre,
                        rs.getDate("dateEmprunt").toLocalDate(),
                        rs.getDate("dateRetour")==null ? null : rs.getDate("dateRetour").toLocalDate()));
            }
            statement.close();
        } catch (SQLException throwables) {
            throw new DaoException();
        }
        return result;
    }

    @Override
    public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
        List<Emprunt> result = new ArrayList<>();
        String query = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,\n" +
                "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,\n" +
                "dateRetour\n" +
                "FROM emprunt AS e\n" +
                "INNER JOIN membre ON membre.id = e.idMembre\n" +
                "INNER JOIN livre ON livre.id = e.idLivre\n" +
                "WHERE dateRetour IS NULL AND membre.id = ?";
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, Integer.toString(idMembre));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Membre membre = new Membre(
                        rs.getInt("idMembre"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        Membre.Abonnement.valueOf(rs.getString("abonnement")));
                Livre livre = new Livre(
                        rs.getInt("idLivre"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("isbn"));
                result.add(new Emprunt(
                        rs.getInt("id"),
                        membre,
                        livre,
                        rs.getDate("dateEmprunt").toLocalDate(),
                        rs.getDate("dateRetour")==null ? null : rs.getDate("dateRetour").toLocalDate()));
            }
            statement.close();
        } catch (SQLException throwables) {
            throw new DaoException();
        }
        return result;
    }

    @Override
    public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
        List<Emprunt> result = new ArrayList<>();
        String query = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,\n" +
                "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,\n" +
                "dateRetour\n" +
                "FROM emprunt AS e\n" +
                "INNER JOIN membre ON membre.id = e.idMembre\n" +
                "INNER JOIN livre ON livre.id = e.idLivre\n" +
                "WHERE dateRetour IS NULL AND livre.id = ?";
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, Integer.toString(idLivre));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Membre membre = new Membre(
                        rs.getInt("idMembre"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        Membre.Abonnement.valueOf(rs.getString("abonnement")));
                Livre livre = new Livre(
                        rs.getInt("idLivre"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("isbn"));
                result.add(new Emprunt(
                        rs.getInt("id"),
                        membre,
                        livre,
                        rs.getDate("dateEmprunt").toLocalDate(),
                        rs.getDate("dateRetour")==null ? null : rs.getDate("dateRetour").toLocalDate()));
            }
            statement.close();
        } catch (SQLException throwables) {
            throw new DaoException();
        }
        return result;
    }

    @Override
    public Emprunt getById(int id) throws DaoException {
        Emprunt result = null;
        String query = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,\n" +
                "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,\n" +
                "dateRetour\n" +
                "FROM emprunt AS e\n" +
                "INNER JOIN membre ON membre.id = e.idMembre\n" +
                "INNER JOIN livre ON livre.id = e.idLivre\n" +
                "WHERE dateRetour IS NULL AND livre.id = ?";
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, Integer.toString(id));
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                Membre membre = new Membre(
                        rs.getInt("idMembre"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        Membre.Abonnement.valueOf(rs.getString("abonnement")));
                Livre livre = new Livre(
                        rs.getInt("idLivre"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("isbn"));
                result = new Emprunt(
                        rs.getInt("id"),
                        membre,
                        livre,
                        rs.getDate("dateEmprunt").toLocalDate(),
                        rs.getDate("dateRetour")==null ? null : rs.getDate("dateRetour").toLocalDate());
            }
            statement.close();
        } catch (SQLException throwables) {
            throw new DaoException();
        }
        return result;
    }

    @Override
    public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException {
        String query = "INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour)\n" +
                "VALUES (?, ?, ?, ?)";
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,Integer.toString(idMembre));
            statement.setString(2,Integer.toString(idLivre));
            statement.setString(3,dateEmprunt.toString());
            statement.setString(4,null);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException throwables) {
            System.out.println(throwables);
            throw new DaoException();
        }
    }

    @Override
    public void update(Emprunt emprunt) throws DaoException {
        String query = "UPDATE emprunt\n" +
                "SET idMembre = ?, idLivre = ?, dateEmprunt = ?, dateRetour = ?\n" +
                "WHERE id = ?";
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,Integer.toString(emprunt.getMembre().getId()));
            statement.setString(2,Integer.toString(emprunt.getLivre().getId()));
            statement.setString(3,emprunt.getDateEmprunt().toString());
            statement.setString(4,emprunt.getDateRetour().toString());
            statement.setString(5,Integer.toString(emprunt.getId()));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException throwables) {
            throw new DaoException();
        }
    }

    @Override
    public int count() throws DaoException {
        int result = 0;
        String query = "SELECT COUNT(id) AS count FROM emprunt";
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

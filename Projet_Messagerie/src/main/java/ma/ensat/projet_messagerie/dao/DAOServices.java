package ma.ensat.projet_messagerie.dao;

import ma.ensat.projet_messagerie.beans.Message;
import ma.ensat.projet_messagerie.beans.Personne;
import ma.ensat.projet_messagerie.util.HashUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOServices {

    public static List<Message> getPublicMessages() {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM Message WHERE idPersonne = 1";

        try (Connection conn = DAOUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Message message = new Message();
                message.setIdMessage(rs.getInt("idMessage"));
                message.setIdPersonne(rs.getInt("idPersonne"));
                message.setSujet(rs.getString("sujet"));
                message.setContenu(rs.getString("contenu"));
                messages.add(message);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des messages publics: " + e.getMessage());
        }
        return messages;
    }

    public static List<String> getPublicSubjects() {
        List<String> subjects = new ArrayList<>();
        String sql = "SELECT sujet FROM Message WHERE idPersonne = 1";

        try (Connection conn = DAOUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                subjects.add(rs.getString("sujet"));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des sujets publics: " + e.getMessage());
        }
        return subjects;
    }

    public static boolean registerUser(String nom, String prenom, String motDePasse) {
        String sql = "INSERT INTO Personne (nom, prenom, motDePasse) VALUES (?, ?, ?)";
        String motDePasseHache = HashUtil.hashPassword(motDePasse);

        try (Connection conn = DAOUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, motDePasseHache);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'inscription de l'utilisateur: " + e.getMessage());
            return false;
        }
    }

    public static Personne authenticateUser(String nom, String prenom, String motDePasse) {
        String sql = "SELECT * FROM Personne WHERE nom = ? AND prenom = ? AND motDePasse = ?";
        String motDePasseHache = HashUtil.hashPassword(motDePasse);

        try (Connection conn = DAOUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, motDePasseHache);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Personne personne = new Personne();
                    personne.setIdPersonne(rs.getInt("idPersonne"));
                    personne.setNom(rs.getString("nom"));
                    personne.setPrenom(rs.getString("prenom"));
                    personne.setMotDePasse(rs.getString("motDePasse"));
                    return personne;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'authentification: " + e.getMessage());
        }
        return null;
    }

    public static List<Message> getUserMessages(int idPersonne) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM Message WHERE idPersonne = ?";

        try (Connection conn = DAOUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPersonne);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Message message = new Message();
                    message.setIdMessage(rs.getInt("idMessage"));
                    message.setIdPersonne(rs.getInt("idPersonne"));
                    message.setSujet(rs.getString("sujet"));
                    message.setContenu(rs.getString("contenu"));
                    messages.add(message);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des messages de l'utilisateur: " + e.getMessage());
        }
        return messages;
    }

    public static List<Personne> getAllPersonnes() {
        List<Personne> personnes = new ArrayList<>();
        String sql = "SELECT * FROM Personne";

        try (Connection conn = DAOUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Personne personne = new Personne();
                personne.setIdPersonne(rs.getInt("idPersonne"));
                personne.setNom(rs.getString("nom"));
                personne.setPrenom(rs.getString("prenom"));
                personne.setMotDePasse(rs.getString("motDePasse"));
                personnes.add(personne);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des personnes: " + e.getMessage());
        }
        return personnes;
    }
}
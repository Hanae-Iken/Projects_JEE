package ma.ensat.projet_messagerie_hibernate.dao;

import ma.ensat.projet_messagerie_hibernate.beans.Message;
import ma.ensat.projet_messagerie_hibernate.beans.Personne;
import ma.ensat.projet_messagerie_hibernate.util.HashUtil;
import ma.ensat.projet_messagerie_hibernate.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class DAOServices {

    public static List<Message> getPublicMessages() {
        List<Message> messages = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<Message> query = session.createQuery("FROM Message WHERE idPersonne = 1", Message.class);
            messages = query.list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Erreur lors de la récupération des messages publics: " + e.getMessage());
        }
        return messages;
    }

    public static List<String> getPublicSubjects() {
        List<String> subjects = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<String> query = session.createQuery("SELECT m.sujet FROM Message m WHERE m.idPersonne = 1", String.class);
            subjects = query.list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Erreur lors de la récupération des sujets publics: " + e.getMessage());
        }
        return subjects;
    }

    public static boolean registerUser(String nom, String prenom, String motDePasse) {
        Transaction transaction = null;
        boolean success = false;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Personne personne = new Personne();
            personne.setNom(nom);
            personne.setPrenom(prenom);
            personne.setMotDePasse(HashUtil.hashPassword(motDePasse));

            session.save(personne);
            transaction.commit();
            success = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Erreur lors de l'inscription de l'utilisateur: " + e.getMessage());
        }
        return success;
    }

    public static Personne authenticateUser(String nom, String prenom, String motDePasse) {
        Transaction transaction = null;
        Personne personne = null;
        String motDePasseHache = HashUtil.hashPassword(motDePasse);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<Personne> query = session.createQuery(
                    "FROM Personne WHERE nom = :nom AND prenom = :prenom AND motDePasse = :motDePasse",
                    Personne.class);
            query.setParameter("nom", nom);
            query.setParameter("prenom", prenom);
            query.setParameter("motDePasse", motDePasseHache);

            personne = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Erreur lors de l'authentification: " + e.getMessage());
        }
        return personne;
    }

    public static List<Message> getUserMessages(int idPersonne) {
        List<Message> messages = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<Message> query = session.createQuery("FROM Message WHERE idPersonne = :idPersonne", Message.class);
            query.setParameter("idPersonne", idPersonne);
            messages = query.list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Erreur lors de la récupération des messages de l'utilisateur: " + e.getMessage());
        }
        return messages;
    }

    public static List<Personne> getAllPersonnes() {
        List<Personne> personnes = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<Personne> query = session.createQuery("FROM Personne", Personne.class);
            personnes = query.list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Erreur lors de la récupération des personnes: " + e.getMessage());
        }
        return personnes;
    }

    // Méthodes supplémentaires pour la gestion complète CRUD

    public static boolean saveMessage(Message message) {
        Transaction transaction = null;
        boolean success = false;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(message);

            transaction.commit();
            success = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Erreur lors de l'enregistrement du message: " + e.getMessage());
        }
        return success;
    }

    public static boolean updateMessage(Message message) {
        Transaction transaction = null;
        boolean success = false;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(message);

            transaction.commit();
            success = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Erreur lors de la mise à jour du message: " + e.getMessage());
        }
        return success;
    }

    public static boolean deleteMessage(int idMessage) {
        Transaction transaction = null;
        boolean success = false;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Message message = session.get(Message.class, idMessage);
            if (message != null) {
                session.delete(message);
                success = true;
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Erreur lors de la suppression du message: " + e.getMessage());
        }
        return success;
    }

    public static Message getMessageById(int idMessage) {
        Transaction transaction = null;
        Message message = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            message = session.get(Message.class, idMessage);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Erreur lors de la récupération du message: " + e.getMessage());
        }
        return message;
    }

    public static Personne getPersonneById(int idPersonne) {
        Transaction transaction = null;
        Personne personne = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            personne = session.get(Personne.class, idPersonne);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Erreur lors de la récupération de la personne: " + e.getMessage());
        }
        return personne;
    }
}
package ma.ensat.hibernate_jsf.service;

import jakarta.enterprise.context.ApplicationScoped;
import ma.ensat.hibernate_jsf.entity.User;
import ma.ensat.hibernate_jsf.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

@ApplicationScoped
public class UserService {

    // Créer un nouvel utilisateur
    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Mettre à jour un utilisateur existant
    public void updateUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Supprimer un utilisateur
    public void deleteUser(int userId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, userId);
            if (user != null) {
                session.remove(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Récupérer un utilisateur par son ID
    public User getUserById(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(User.class, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Récupérer tous les utilisateurs
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
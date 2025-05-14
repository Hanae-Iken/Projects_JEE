package ma.ensat.hibernate_jsf_projet.service;

import jakarta.enterprise.context.ApplicationScoped;
import ma.ensat.hibernate_jsf_projet.entity.Auto;
import ma.ensat.hibernate_jsf_projet.entity.User;
import ma.ensat.hibernate_jsf_projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

@ApplicationScoped
public class AutoService {

    // Créer une nouvelle voiture
    public void saveAuto(Auto auto) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(auto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Mettre à jour une voiture existante
    public void updateAuto(Auto auto) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(auto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Supprimer une voiture
    public void deleteAuto(int autoId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Auto auto = session.get(Auto.class, autoId);
            if (auto != null) {
                session.remove(auto);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Récupérer une voiture par son ID
    public Auto getAutoById(int autoId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Auto.class, autoId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Récupérer toutes les voitures
    @SuppressWarnings("unchecked")
    public List<Auto> getAllAutos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Auto").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Récupérer toutes les voitures d'un utilisateur spécifique
    @SuppressWarnings("unchecked")
    public List<Auto> getAutosByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Auto a WHERE a.user = :user")
                    .setParameter("user", user)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
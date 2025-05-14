package ma.ensat.projet_messagerie_hibernate.beans;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Personne")
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersonne")
    private int idPersonne;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "motDePasse")
    private String motDePasse;

    @OneToMany(mappedBy = "personne", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> messages = new ArrayList<>();

    public Personne() {
    }

    public Personne(int idPersonne, String nom, String prenom, String motDePasse) {
        this.idPersonne = idPersonne;
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
    }

    // Getters et Setters
    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    // Helper methods for bidirectional relationship
    public void addMessage(Message message) {
        messages.add(message);
        message.setPersonne(this);
    }

    public void removeMessage(Message message) {
        messages.remove(message);
        message.setPersonne(null);
    }

    @Override
    public String toString() {
        return "Personne{" +
                "idPersonne=" + idPersonne +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                '}';
    }
}
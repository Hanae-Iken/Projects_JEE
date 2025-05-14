package ma.ensat.projet_messagerie_hibernate.beans;


import jakarta.persistence.*;

//import javax.persistence.*;

@Entity
@Table(name = "Message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMessage")
    private int idMessage;

    @Column(name = "idPersonne")
    private int idPersonne; // lien vers l'auteur

    @Column(name = "sujet")
    private String sujet;

    @Column(name = "contenu")
    private String contenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersonne", insertable = false, updatable = false)
    private Personne personne;

    public Message() {
    }

    public Message(int idMessage, int idPersonne, String sujet, String contenu) {
        this.idMessage = idMessage;
        this.idPersonne = idPersonne;
        this.sujet = sujet;
        this.contenu = contenu;
    }

    // Getters et Setters
    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    @Override
    public String toString() {
        return "Message{" +
                "idMessage=" + idMessage +
                ", idPersonne=" + idPersonne +
                ", sujet='" + sujet + '\'' +
                ", contenu='" + contenu + '\'' +
                '}';
    }
}
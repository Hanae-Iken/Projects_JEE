package ma.ensat.projet_messagerie.beans;

public class Message {
    private int idMessage;
    private int idPersonne; // lien vers l'auteur
    private String sujet;
    private String contenu;

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
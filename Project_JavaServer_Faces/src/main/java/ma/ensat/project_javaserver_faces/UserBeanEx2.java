package ma.ensat.project_javaserver_faces;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;

import java.util.Date;

@Named("userBean")
@RequestScoped
public class UserBeanEx2 {
    private String prenom;
    private String nom;
    private Date datenaissance;
    private String email;
    private String genre;

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prénom) {
        this.prenom = prénom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void validateEmail(FacesContext context, UIComponent toValidate,
                              Object value) throws ValidatorException {

        String eMail =(String) value;
        if (eMail.indexOf("@") < 0){
            FacesMessage message =new FacesMessage("Adresse Email invalide !");
            throw new ValidatorException(message);
        }
    }
}

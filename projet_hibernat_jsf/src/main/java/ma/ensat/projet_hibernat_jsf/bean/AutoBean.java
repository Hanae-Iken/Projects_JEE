package ma.ensat.projet_hibernat_jsf.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;  // Changement de ViewScoped à SessionScoped
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import ma.ensat.projet_hibernat_jsf.entity.Auto;
import ma.ensat.projet_hibernat_jsf.entity.User;
import ma.ensat.projet_hibernat_jsf.service.AutoService;
import ma.ensat.projet_hibernat_jsf.service.UserService;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped  // Utiliser SessionScoped au lieu de ViewScoped pour assurer la persistance
public class AutoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private AutoService autoService;

    @Inject
    private UserService userService;

    private List<Auto> autos;
    private Auto auto = new Auto();
    private int selectedUserId;
    private boolean editMode = false;

    @PostConstruct
    public void init() {
        loadAutos();
    }

    public void loadAutos() {
        autos = autoService.getAllAutos();
    }

    public String saveAuto() {
        try {
            // Récupérer l'utilisateur sélectionné
            User selectedUser = userService.getUserById(selectedUserId);

            if (selectedUser != null) {
                // Associer l'utilisateur à l'automobile
                auto.setUser(selectedUser);

                if (editMode) {
                    autoService.updateAuto(auto);
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Automobile mise à jour avec succès", null));
                } else {
                    // Sauvegarde de la nouvelle automobile
                    autoService.saveAuto(auto);
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Automobile ajoutée avec succès", null));
                }

                // Recharger les automobiles et les utilisateurs pour mettre à jour l'affichage
                loadAutos();

                // Réinitialiser le formulaire
                resetAuto();

                // Rafraîchir également la liste des utilisateurs
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

                return null;
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Veuillez sélectionner un utilisateur valide", null));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur: " + e.getMessage(), null));
            e.printStackTrace();
        }
        return null;
    }

    public String editAuto(Auto auto) {
        this.auto = auto;
        this.selectedUserId = auto.getUser() != null ? auto.getUser().getId() : 0;
        editMode = true;
        return null;
    }

    public String deleteAuto(int autoId) {
        try {
            autoService.deleteAuto(autoId);
            loadAutos();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Automobile supprimée avec succès", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur: " + e.getMessage(), null));
            e.printStackTrace();
        }
        return null;
    }

    public void resetAuto() {
        this.auto = new Auto();
        this.selectedUserId = 0;
        editMode = false;
    }

    // Getters et Setters
    public List<Auto> getAutos() {
        return autos;
    }

    public void setAutos(List<Auto> autos) {
        this.autos = autos;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public int getSelectedUserId() {
        return selectedUserId;
    }

    public void setSelectedUserId(int selectedUserId) {
        this.selectedUserId = selectedUserId;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public List<User> getUsersList() {
        return userService.getAllUsers();
    }
}
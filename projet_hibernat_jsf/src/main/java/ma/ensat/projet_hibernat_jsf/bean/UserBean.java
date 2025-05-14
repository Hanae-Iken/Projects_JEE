package ma.ensat.projet_hibernat_jsf.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import ma.ensat.projet_hibernat_jsf.entity.User;
import ma.ensat.projet_hibernat_jsf.service.UserService;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped  // Changement de SessionScoped à ViewScoped pour éviter les problèmes de synchronisation
public class UserBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private UserService userService;

    private List<User> users;
    private User user = new User();
    private User selectedUser;
    private boolean editing = false;

    @PostConstruct
    public void init() {
        loadUsers();
    }

    public void loadUsers() {
        users = userService.getAllUsers();
    }

    public String saveUser() {
        try {
            if (editing) {
                userService.updateUser(selectedUser);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Utilisateur mis à jour avec succès", null));
            } else {
                userService.saveUser(user);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Utilisateur ajouté avec succès", null));
            }
            resetUser();
            loadUsers();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur: " + e.getMessage(), null));
            e.printStackTrace();
        }
        return null;
    }

    public String prepareEdit(User user) {
        this.selectedUser = user;
        this.editing = true;
        return null;
    }

    public String cancelEdit() {
        resetUser();
        return null;
    }

    public String deleteUser(int userId) {
        try {
            userService.deleteUser(userId);
            loadUsers();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Utilisateur supprimé avec succès", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur: " + e.getMessage(), null));
            e.printStackTrace();
        }
        return null;
    }

    public void resetUser() {
        this.user = new User();
        this.selectedUser = null;
        this.editing = false;
    }

    // Getters et Setters
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }
}
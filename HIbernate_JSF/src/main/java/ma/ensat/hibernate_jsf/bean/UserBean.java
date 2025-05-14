package ma.ensat.hibernate_jsf.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import ma.ensat.hibernate_jsf.entity.User;
import ma.ensat.hibernate_jsf.service.UserService;

import java.io.Serializable;
import java.util.List;

@Named("userBean")
@SessionScoped
public class UserBean implements Serializable {

    private User user = new User();
    private User selectedUser;
    private List<User> userList;
    private final UserService userService = new UserService();

    @PostConstruct
    public void init() {
        loadUsers();
    }

    public void loadUsers() {
        userList = userService.getAllUsers();
    }

    public String saveUser() {
        try {
            userService.saveUser(user);
            user = new User(); // Reset the form
            loadUsers(); // Refresh the list
            addMessage(FacesMessage.SEVERITY_INFO, "Succès", "Utilisateur ajouté avec succès");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Erreur lors de l'ajout de l'utilisateur");
            e.printStackTrace();
        }
        return null;
    }

    public String updateUser() {
        try {
            userService.updateUser(selectedUser);
            selectedUser = null; // Reset the selection
            loadUsers(); // Refresh the list
            addMessage(FacesMessage.SEVERITY_INFO, "Succès", "Utilisateur mis à jour avec succès");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Erreur lors de la mise à jour de l'utilisateur");
            e.printStackTrace();
        }
        return null;
    }

    public void deleteUser(int id) {
        try {
            userService.deleteUser(id);
            loadUsers(); // Refresh the list
            addMessage(FacesMessage.SEVERITY_INFO, "Succès", "Utilisateur supprimé avec succès");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Erreur lors de la suppression de l'utilisateur");
            e.printStackTrace();
        }
    }

    public void selectForUpdate(User user) {
        this.selectedUser = user;
    }

    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    // Getters and setters
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

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
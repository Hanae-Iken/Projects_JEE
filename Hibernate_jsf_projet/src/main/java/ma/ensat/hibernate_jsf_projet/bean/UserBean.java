package ma.ensat.hibernate_jsf_projet.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import ma.ensat.hibernate_jsf_projet.entity.User;
import ma.ensat.hibernate_jsf_projet.service.UserService;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UserBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private UserService userService;

    private List<User> userList;
    private User user = new User();
    private boolean editMode = false;

    @PostConstruct
    public void init() {
        loadUsers();
    }

    public void loadUsers() {
        userList = userService.getAllUsers();
    }

    public String saveUser() {
        if (editMode) {
            userService.updateUser(user);
        } else {
            userService.saveUser(user);
        }
        resetUser();
        loadUsers();
        return null;
    }

    public String editUser(User user) {
        this.user = user;
        editMode = true;
        return null;
    }

    public String deleteUser(int userId) {
        userService.deleteUser(userId);
        loadUsers();
        return null;
    }

    public void resetUser() {
        this.user = new User();
        editMode = false;
    }

    // Getters et Setters
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }
}
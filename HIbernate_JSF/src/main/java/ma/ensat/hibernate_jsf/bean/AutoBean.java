package ma.ensat.hibernate_jsf.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import ma.ensat.hibernate_jsf.entity.Auto;
import ma.ensat.hibernate_jsf.entity.User;
import ma.ensat.hibernate_jsf.service.AutoService;
import ma.ensat.hibernate_jsf.service.UserService;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class AutoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private AutoService autoService;

    @Inject
    private UserService userService;

    private List<Auto> autoList;
    private Auto auto = new Auto();
    private int selectedUserId;
    private boolean editMode = false;

    @PostConstruct
    public void init() {
        loadAutos();
    }

    public void loadAutos() {
        autoList = autoService.getAllAutos();
    }

    public String saveAuto() {
        // Récupérer l'utilisateur sélectionné
        User selectedUser = userService.getUserById(selectedUserId);
        if (selectedUser != null) {
            auto.setUser(selectedUser);

            if (editMode) {
                autoService.updateAuto(auto);
            } else {
                autoService.saveAuto(auto);
            }

            resetAuto();
            loadAutos();
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
        autoService.deleteAuto(autoId);
        loadAutos();
        return null;
    }

    public void resetAuto() {
        this.auto = new Auto();
        this.selectedUserId = 0;
        editMode = false;
    }

    // Getters et Setters
    public List<Auto> getAutoList() {
        return autoList;
    }

    public void setAutoList(List<Auto> autoList) {
        this.autoList = autoList;
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

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
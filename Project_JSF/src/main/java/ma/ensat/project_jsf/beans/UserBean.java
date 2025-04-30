package ma.ensat.project_jsf.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named("user")
@RequestScoped
public class UserBean {
    private String name;
    public String getName() {
        System.out.println("je suis dans get");
        return name;
    }
    public void setName(String name) {
        System.out.println("je suis dans set");
        this.name = name;
    }
    public String returnAction(){
        System.out.println("Je suis dans returnAction");
        return (name.equals("houda")?"ok":"not ok");
    }
}

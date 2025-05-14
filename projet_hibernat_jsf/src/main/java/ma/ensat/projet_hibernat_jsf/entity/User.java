package ma.ensat.projet_hibernat_jsf.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int age;

    // Modifié pour résoudre le problème de LazyInitializationException
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Auto> autos = new ArrayList<>();

    // Constructeurs
    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Auto> getAutos() {
        return autos;
    }

    public void setAutos(List<Auto> autos) {
        this.autos = autos;
    }

    // Méthodes utilitaires pour gérer la relation bidirectionnelle
    public void addAuto(Auto auto) {
        autos.add(auto);
        auto.setUser(this);
    }

    public void removeAuto(Auto auto) {
        autos.remove(auto);
        auto.setUser(null);
    }

    @Override
    public String toString() {
        return name + " (" + age + " ans)";
    }
}
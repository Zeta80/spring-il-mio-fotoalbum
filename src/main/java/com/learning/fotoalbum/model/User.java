package com.learning.fotoalbum.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.Set;

@Entity
@Table(name= "users")
public class User {
    //ATTRIBUTES / COLUMNS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String firstName;
    private String lastName;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    //RELATIONS
    @ManyToMany(fetch = FetchType.EAGER)
    //usiamo fetch in modo che appena venga selezionato un utente devono vengano subito selezionati i suoi ruoli
    private Set<Role> roles;

    //GETTER & SETTER

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

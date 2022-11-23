package com.projectblog.blog.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    private String pseudo;
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public User() {
    }

    public User(String pseudo, String password) {
        this.setId(id);
        this.setPseudo(pseudo);
        this.setPassword(password);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

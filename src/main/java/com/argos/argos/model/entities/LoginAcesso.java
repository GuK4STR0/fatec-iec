package com.argos.argos.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LoginAcesso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idAcesso;
    private String senhaAcesso;

    public LoginAcesso(){}

    public LoginAcesso(String idAcesso, String senhaAcesso) {
        this.idAcesso = idAcesso;
        this.senhaAcesso = senhaAcesso;
    }

    public Long getId() {
        return id;
    }

    public String getIdAcesso() {
        return idAcesso;
    }

    public void setIdAcesso(String idAcesso) {
        this.idAcesso = idAcesso;
    }

    public String getSenhaAcesso() {
        return senhaAcesso;
    }

    public void setSenhaAcesso(String senhaAcesso) {
        this.senhaAcesso = senhaAcesso;
    }
}

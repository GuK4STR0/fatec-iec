package com.argos.argos.model.entities;

import jakarta.persistence.*;

@Entity
public class Dependente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String rg;

    @ManyToOne
    @JoinColumn(name = "idResponsavel", referencedColumnName = "id")
    private Responsavel responsavel;

    public Dependente() {
    }

    public Dependente(String nome, String rg, Responsavel responsavel) {
        this.nome = nome;
        this.rg = rg;
        this.responsavel = responsavel;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }
}

package com.argos.argos.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HistoricoTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String horarioRegistro;

    public HistoricoTag() {}

    public HistoricoTag(String horarioRegistro) {
        this.horarioRegistro = horarioRegistro;
    }

    public Long getId() {
        return id;
    }

    public String getHorarioRegistro() {
        return horarioRegistro;
    }

    public void setHorarioRegistro(String horarioRegistro) {
        this.horarioRegistro = horarioRegistro;
    }
}

package com.argos.argos.model.entities;

import jakarta.persistence.*;

@Entity
public class Trancas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idRegistroTranca;

    @OneToOne
    @JoinColumn(name = "idLocalidades", referencedColumnName = "id")
    private Localidades localidades;

    public Trancas() {}

    public Trancas(Long idRegistroTranca, Localidades localidades) {
        this.idRegistroTranca = idRegistroTranca;
        this.localidades = localidades;
    }

    public Long getId() {
        return id;
    }

    public Long getIdRegistroTranca() {
        return idRegistroTranca;
    }

    public void setIdRegistroTranca(Long idRegistroTranca) {
        this.idRegistroTranca = idRegistroTranca;
    }

    public Localidades getLocalidades() {
        return localidades;
    }

    public void setLocalidades(Localidades localidades) {
        this.localidades = localidades;
    }
}

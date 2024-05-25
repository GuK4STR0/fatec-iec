package com.argos.argos.model.entities;

import jakarta.persistence.*;

@Entity
public class TagTranca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idTag", referencedColumnName = "id")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "idTranca", referencedColumnName = "id")
    private Trancas trancas;

    public TagTranca() {
    }

    public TagTranca(Tag tag, Trancas trancas) {
        this.tag = tag;
        this.trancas = trancas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Trancas getTrancas() {
        return trancas;
    }

    public void setTrancas(Trancas trancas) {
        this.trancas = trancas;
    }
}

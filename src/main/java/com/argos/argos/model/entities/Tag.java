package com.argos.argos.model.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isTemporario;
    private LocalDate horarioInicio;
    private LocalDate horarioFim;
    private String codRegistroTag;

    @ManyToOne
    @JoinColumn(name = "idResponsavel", referencedColumnName = "id")
    private Responsavel responsavel;

    @ManyToOne
    @JoinColumn(name = "idDependente", referencedColumnName = "id")
    private Dependente dependente;

    @ManyToOne
    @JoinColumn(name = "idHistoricoTag", referencedColumnName = "id")
    private HistoricoTag historicoTag;

    public Tag() {
    }

    public Tag(boolean isTemporario, LocalDate horarioInicio, LocalDate horarioFim, String codRegistroTag, Responsavel responsavel, Dependente dependente, HistoricoTag historicoTag) {
        this.isTemporario = isTemporario;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.codRegistroTag = codRegistroTag;
        this.responsavel = responsavel;
        this.dependente = dependente;
        this.historicoTag = historicoTag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getIsTemporario() {
        return isTemporario;
    }

    public void setIsTemporario(boolean isTemporario) {
        this.isTemporario = isTemporario;
    }

    public LocalDate getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalDate horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalDate getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalDate horarioFim) {
        this.horarioFim = horarioFim;
    }

    public String getCodRegistroTag() {
        return codRegistroTag;
    }

    public void setCodRegistroTag(String codRegistroTag) {
        this.codRegistroTag = codRegistroTag;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public Dependente getDependente() {
        return dependente;
    }

    public void setDependente(Dependente dependente) {
        this.dependente = dependente;
    }

    public HistoricoTag getHistoricoTag() {
        return historicoTag;
    }

    public void setHistoricoTag(HistoricoTag historicoTag) {
        this.historicoTag = historicoTag;
    }
}

package com.argos.argos.service;

import com.argos.argos.model.entities.Responsavel;

import java.util.List;
import java.util.Optional;

public interface IResponsavelService {
    public List<Responsavel> find();

    public Optional<Responsavel> findById(Long id);

    public Optional<Responsavel> insert(Responsavel obj);

    public Optional<Responsavel> update(Responsavel obj);

    public void delete(Long id);
}

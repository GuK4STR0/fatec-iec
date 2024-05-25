package com.argos.argos.service;

import com.argos.argos.model.entities.Localidades;

import java.util.List;
import java.util.Optional;

public interface ILocalidadesService {
    public List<Localidades> find();

    public Optional<Localidades> findById(Long id);

    public Optional<Localidades> insert(Localidades obj);

    public Optional<Localidades> update(Localidades obj);

    public void delete(Long id);
}

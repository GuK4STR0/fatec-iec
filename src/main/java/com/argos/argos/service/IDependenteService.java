package com.argos.argos.service;

import com.argos.argos.model.entities.Dependente;

import java.util.List;
import java.util.Optional;

public interface IDependenteService {
    public List<Dependente> find();

    public Optional<Dependente> findById(Long id);

    public Optional<Dependente> insert(Dependente obj);

    public Optional<Dependente> update(Dependente obj);

    public void delete(Long id);
}

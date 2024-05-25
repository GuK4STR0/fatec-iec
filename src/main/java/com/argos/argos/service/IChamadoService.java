package com.argos.argos.service;

import com.argos.argos.model.entities.Chamado;

import java.util.List;
import java.util.Optional;

public interface IChamadoService {
    public List<Chamado> find();

    public Optional<Chamado> findById(Long id);

    public Optional<Chamado> insert(Chamado obj, Long id);

    public Optional<Chamado> update(Chamado obj);

    public void delete(Long id);
}

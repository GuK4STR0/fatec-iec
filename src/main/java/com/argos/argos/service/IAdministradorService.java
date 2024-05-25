package com.argos.argos.service;

import com.argos.argos.model.entities.Administrador;

import java.util.List;
import java.util.Optional;

public interface IAdministradorService {

    public List<Administrador> find();

    public Optional<Administrador> findById(Long id);

    public Optional<Administrador> insert(Administrador obj);

    public Optional<Administrador> update(Administrador obj);

    public void delete(Long id);
}

package com.argos.argos.service;

import com.argos.argos.model.entities.Trancas;

import java.util.List;
import java.util.Optional;

public interface ITrancasService {
    public List<Trancas> find();

    public Optional<Trancas> findById(Long id);

    public Optional<Trancas> insert(Trancas obj);

    public Optional<Trancas> update(Trancas obj);

    public void delete(Long id);
}

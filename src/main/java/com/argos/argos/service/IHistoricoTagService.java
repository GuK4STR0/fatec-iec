package com.argos.argos.service;

import com.argos.argos.model.entities.HistoricoTag;

import java.util.List;
import java.util.Optional;


public interface IHistoricoTagService {
    public List<HistoricoTag> find();

    public Optional<HistoricoTag> findById(Long id);

    public Optional<HistoricoTag> insert(HistoricoTag obj);

    public Optional<HistoricoTag> update(HistoricoTag obj);

    public void delete(Long id);
}

package com.argos.argos.service;

import com.argos.argos.model.entities.TagTranca;

import java.util.List;
import java.util.Optional;

public interface ITagTrancaService {
    public List<TagTranca> find();

    public Optional<TagTranca> findById(Long id);

    public Optional<TagTranca> insert(TagTranca obj);

    public Optional<TagTranca> update(TagTranca obj);

    public void delete(Long id);
}

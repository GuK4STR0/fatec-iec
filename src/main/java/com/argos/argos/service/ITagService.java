package com.argos.argos.service;

import com.argos.argos.model.entities.Tag;

import java.util.List;
import java.util.Optional;

public interface ITagService {
    public List<Tag> find();

    public Optional<Tag> findById(Long id);

    public Optional<Tag> insert(Tag obj);

    public Optional<Tag> update(Tag obj);

    public void delete(Long id);
}

package com.argos.argos.service.impl;

import com.argos.argos.model.entities.TagTranca;
import com.argos.argos.model.repositories.ITagTrancaRepository;
import com.argos.argos.service.ITagTrancaService;
import com.argos.argos.service.exception.DatabaseException;
import com.argos.argos.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagTrancaService implements ITagTrancaService {

    private Logger log = LogManager.getLogger(TagTrancaService.class);
    private final ITagTrancaRepository tagTrancaRepository;

    public TagTrancaService(ITagTrancaRepository tagTrancaRepository) {
        this.tagTrancaRepository = tagTrancaRepository;
    }

    @Override
    public List<TagTranca> find() {
        log.info(">>>> [TagTrancaService] find iniciado");

        return tagTrancaRepository.findAll();
    }

    @Override
    public Optional<TagTranca> findById(Long id) {
        log.info(">>>> [TagTrancaService] findById(" + id +") iniciado");

        Optional<TagTranca> tagTranca = tagTrancaRepository.findById(id);

        return Optional.ofNullable(tagTranca.orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    @Override
    public Optional<TagTranca> insert(TagTranca obj) {
        log.info(">>>> [TagTrancaService] insert iniciado");

        return Optional.of(tagTrancaRepository.save(obj));
    }

    @Override
    public Optional<TagTranca> update(TagTranca obj) {
        log.info(">>>> [TagTrancaService update iniciado]");
        try{
            TagTranca entidade = tagTrancaRepository.getReferenceById(obj.getId());
            updateData(entidade, obj);
            return Optional.of(tagTrancaRepository.save(entidade));
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(obj.getId());
        }
    }

    private void updateData(TagTranca entidade, TagTranca obj){
        entidade.setTag(obj.getTag());
        entidade.setTrancas(obj.getTrancas());
    }

    @Override
    public void delete(Long id) {
        log.info(">>>> [delete iniciado]");
        try{
            deleteData(id);
        } catch (EmptyResultDataAccessException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void deleteData(Long id){
        Optional<TagTranca> tagTranca = findById(id);
        tagTrancaRepository.deleteById(id);
    }
}
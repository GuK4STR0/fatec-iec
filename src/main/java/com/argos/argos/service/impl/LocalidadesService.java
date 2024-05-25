package com.argos.argos.service.impl;

import com.argos.argos.model.entities.Localidades;
import com.argos.argos.model.repositories.ILocalidadesRepository;
import com.argos.argos.service.ILocalidadesService;
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
public class LocalidadesService implements ILocalidadesService {

    private Logger log = LogManager.getLogger(LocalidadesService.class);
    private final ILocalidadesRepository localidadesRepository;

    public LocalidadesService(ILocalidadesRepository localidadesRepository) {
        this.localidadesRepository = localidadesRepository;
    }

    @Override
    public List<Localidades> find() {
        log.info(">>>> [LocalidadesService] find iniciado");

        return localidadesRepository.findAll();
    }

    @Override
    public Optional<Localidades> findById(Long id) {
        log.info(">>>> [LocalidadesService] findById(" + id +") iniciado");

        Optional<Localidades> localidades = localidadesRepository.findById(id);

        return Optional.ofNullable(localidades.orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    @Override
    public Optional<Localidades> insert(Localidades obj) {
        log.info(">>>> [LocalidadesService] insert iniciado");

        return Optional.of(localidadesRepository.save(obj));
    }

    @Override
    public Optional<Localidades> update(Localidades obj) {
        log.info(">>>> [LocalidadesService update iniciado]");
        try{
            Localidades entidade = localidadesRepository.getReferenceById(obj.getId());
            updateData(entidade, obj);
            return Optional.of(localidadesRepository.save(entidade));
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(obj.getId());
        }
    }

    private void updateData(Localidades entidade, Localidades obj){
        entidade.setDescLocal(obj.getDescLocal());
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
        Optional<Localidades> localidades = findById(id);
        localidadesRepository.deleteById(id);
    }
}
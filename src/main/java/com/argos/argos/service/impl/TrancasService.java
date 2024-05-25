package com.argos.argos.service.impl;

import com.argos.argos.model.entities.Trancas;
import com.argos.argos.model.repositories.ITrancasRepository;
import com.argos.argos.service.ITrancasService;
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
public class TrancasService implements ITrancasService {

    private Logger log = LogManager.getLogger(TrancasService.class);
    private final ITrancasRepository trancasRepository;

    public TrancasService(ITrancasRepository trancasRepository) {
        this.trancasRepository = trancasRepository;
    }

    @Override
    public List<Trancas> find() {
        log.info(">>>> [TrancasService] find iniciado");

        return trancasRepository.findAll();
    }

    @Override
    public Optional<Trancas> findById(Long id) {
        log.info(">>>> [TrancasService] findById(" + id +") iniciado");

        Optional<Trancas> trancas = trancasRepository.findById(id);

        return Optional.ofNullable(trancas.orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    @Override
    public Optional<Trancas> insert(Trancas obj) {
        log.info(">>>> [TrancasService] insert iniciado");

        return Optional.of(trancasRepository.save(obj));
    }

    @Override
    public Optional<Trancas> update(Trancas obj) {
        log.info(">>>> [TrancasService update iniciado]");
        try{
            Trancas entidade = trancasRepository.getReferenceById(obj.getId());
            updateData(entidade, obj);
            return Optional.of(trancasRepository.save(entidade));
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(obj.getId());
        }
    }

    private void updateData(Trancas entidade, Trancas obj){
        entidade.setIdRegistroTranca(obj.getIdRegistroTranca());
        entidade.setLocalidades(obj.getLocalidades());
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
        Optional<Trancas> trancas = findById(id);
        trancasRepository.deleteById(id);
    }
}
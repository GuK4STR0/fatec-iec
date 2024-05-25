package com.argos.argos.service.impl;

import com.argos.argos.model.entities.HistoricoTag;
import com.argos.argos.model.repositories.IHistoricoTagRepository;
import com.argos.argos.service.IHistoricoTagService;
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
public class HistoricoTagService implements IHistoricoTagService {

    private Logger log = LogManager.getLogger(HistoricoTagService.class);
    private final IHistoricoTagRepository historicoTagRepository;

    public HistoricoTagService(IHistoricoTagRepository historicoTagRepository) {
        this.historicoTagRepository = historicoTagRepository;
    }

    @Override
    public List<HistoricoTag> find() {
        log.info(">>>> [HistoricoTagService] find iniciado");

        return historicoTagRepository.findAll();
    }

    @Override
    public Optional<HistoricoTag> findById(Long id) {
        log.info(">>>> [HistoricoTagService] findById(" + id +") iniciado");

        Optional<HistoricoTag> historicoTag = historicoTagRepository.findById(id);

        return Optional.ofNullable(historicoTag.orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    @Override
    public Optional<HistoricoTag> insert(HistoricoTag obj) {
        log.info(">>>> [HistoricoTagService] insert iniciado");

        return Optional.of(historicoTagRepository.save(obj));
    }

    @Override
    public Optional<HistoricoTag> update(HistoricoTag obj) {
        log.info(">>>> [HistoricoTagService update iniciado]");
        try{
            HistoricoTag entidade = historicoTagRepository.getReferenceById(obj.getId());
            updateData(entidade, obj);
            return Optional.of(historicoTagRepository.save(entidade));
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(obj.getId());
        }
    }

    private void updateData(HistoricoTag entidade, HistoricoTag obj){
        entidade.setHorarioRegistro(obj.getHorarioRegistro());
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
        Optional<HistoricoTag> historicoTag = findById(id);
        historicoTagRepository.deleteById(id);
    }
}
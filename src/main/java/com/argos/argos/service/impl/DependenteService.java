package com.argos.argos.service.impl;

import com.argos.argos.model.entities.Dependente;
import com.argos.argos.model.repositories.IDependenteRepository;
import com.argos.argos.service.IDependenteService;
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
public class DependenteService implements IDependenteService {

    private Logger log = LogManager.getLogger(DependenteService.class);
    private final IDependenteRepository dependenteRepository;

    public DependenteService(IDependenteRepository dependenteRepository) {
        this.dependenteRepository = dependenteRepository;
    }

    @Override
    public List<Dependente> find() {
        log.info(">>>> [DependenteService] find iniciado");

        return dependenteRepository.findAll();
    }

    @Override
    public Optional<Dependente> findById(Long id) {
        log.info(">>>> [DependenteService] findById(" + id +") iniciado");

        Optional<Dependente> dependente = dependenteRepository.findById(id);

        return Optional.ofNullable(dependente.orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    @Override
    public Optional<Dependente> insert(Dependente obj) {
        log.info(">>>> [DependenteService] insert iniciado");

        return Optional.of(dependenteRepository.save(obj));
    }

    @Override
    public Optional<Dependente> update(Dependente obj) {
        log.info(">>>> [DependenteService update iniciado]");
        try{
            Dependente entidade = dependenteRepository.getReferenceById(obj.getId());
            updateData(entidade, obj);
            return Optional.of(dependenteRepository.save(entidade));
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(obj.getId());
        }
    }

    private void updateData(Dependente entidade, Dependente obj){
        entidade.setNome(obj.getNome());
        entidade.setRg(obj.getRg());
        entidade.setResponsavel(obj.getResponsavel());
        // Adicione aqui outros campos que você deseja atualizar
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
        Optional<Dependente> dependente = findById(id);
        dependenteRepository.deleteById(id);
    }
}
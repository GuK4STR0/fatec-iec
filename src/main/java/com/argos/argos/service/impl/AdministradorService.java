package com.argos.argos.service.impl;

import com.argos.argos.model.entities.Administrador;
import com.argos.argos.model.repositories.IAdministradorRepository;
import com.argos.argos.service.IAdministradorService;
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
public class AdministradorService implements IAdministradorService {

    private Logger log = LogManager.getLogger(AdministradorService.class);
    private final IAdministradorRepository administradorRepository;

    public AdministradorService(IAdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    @Override
    public List<Administrador> find() {
        log.info(">>>> [AdministradorService] find iniciado");

        return administradorRepository.findAll();
    }

    @Override
    public Optional<Administrador> findById(Long id) {
        log.info(">>>> [AdministradorService] findById(" + id +") iniciado");

        Optional<Administrador> administrador = administradorRepository.findById(id);

        return Optional.ofNullable(administrador.orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    @Override
    public Optional<Administrador> insert(Administrador obj) {
        log.info(">>>> [AdministradorService] insert iniciado");

        return Optional.of(administradorRepository.save(obj));
    }

    @Override
    public Optional<Administrador> update(Administrador obj) {
        log.info(">>>> [AdministradorService update iniciado]");
        try{
            Administrador entidade = administradorRepository.getReferenceById(obj.getId());
            updateData(entidade, obj);
            return Optional.of(administradorRepository.save(entidade));
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(obj.getId());
        }
    }

    private void updateData(Administrador entidade, Administrador obj){
        entidade.setNome(obj.getNome());
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
        Optional<Administrador> administrador = findById(id);
        administradorRepository.deleteById(id);
    }
}
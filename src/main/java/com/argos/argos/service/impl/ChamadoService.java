package com.argos.argos.service.impl;

import com.argos.argos.model.entities.Administrador;
import com.argos.argos.model.entities.Chamado;
import com.argos.argos.model.entities.Responsavel;
import com.argos.argos.model.repositories.IAdministradorRepository;
import com.argos.argos.model.repositories.IChamadoRepository;
import com.argos.argos.model.repositories.IResponsavelRepository;
import com.argos.argos.service.IChamadoService;
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
public class ChamadoService implements IChamadoService {

    private Logger log = LogManager.getLogger(ChamadoService.class);
    private final IChamadoRepository chamadoRepository;
    private final IResponsavelRepository responsavelRepository;

    public ChamadoService(IChamadoRepository chamadoRepository, IResponsavelRepository responsavelRepository) {
        this.chamadoRepository = chamadoRepository;
        this.responsavelRepository = responsavelRepository;
    }

    @Override
    public List<Chamado> find() {
        log.info(">>>> [ChamadoService] find iniciado");

        return chamadoRepository.findAll();
    }

    @Override
    public Optional<Chamado> findById(Long id) {
        log.info(">>>> [ChamadoService] findById(" + id +") iniciado");

        Optional<Chamado> chamado = chamadoRepository.findById(id);

        return Optional.ofNullable(chamado.orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    @Override
    public Optional<Chamado> insert(Chamado obj, Long id) {
        log.info(">>>> [ChamadoService] insert iniciado");

        Optional<Responsavel> responsavel = responsavelRepository.findById(id);
        obj.setResponsavel(responsavel.get());

        return Optional.ofNullable(chamadoRepository.save(obj));
    }

    @Override
    public Optional<Chamado> update(Chamado obj) {
        log.info(">>>> [ChamadoService update iniciado]");
        try{
            Chamado entidade = chamadoRepository.getReferenceById(obj.getId());
            updateData(entidade, obj);
            return Optional.of(chamadoRepository.save(entidade));
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(obj.getId());
        }
    }

    private void updateData(Chamado entidade, Chamado obj){
        entidade.setTipoChamado(obj.getTipoChamado());
        entidade.setDependenteRg(obj.getDependenteRg());
        entidade.setIsTagTemporaria(obj.getIsTagTemporaria());
        entidade.setHorarioInicioTag(obj.getHorarioInicioTag().toString());
        entidade.setHorarioFimTag(obj.getHorarioFimTag().toString());
        entidade.setResponsavel(obj.getResponsavel());
        entidade.setMotivo(obj.getMotivo());
        entidade.setDependenteNome(obj.getDependenteNome());
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
        Optional<Chamado> chamado = findById(id);
        chamadoRepository.deleteById(id);
    }
}
package com.argos.argos.service.impl;

import com.argos.argos.model.entities.LoginAcesso;
import com.argos.argos.model.entities.Responsavel;
import com.argos.argos.model.repositories.ILoginAcessoRepository;
import com.argos.argos.model.repositories.IResponsavelRepository;
import com.argos.argos.service.IResponsavelService;
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
public class ResponsavelService implements IResponsavelService {

    private Logger log = LogManager.getLogger(ResponsavelService.class);
    private final IResponsavelRepository responsavelRepository;
    private final ILoginAcessoRepository loginAcessoRepository;

    public ResponsavelService(IResponsavelRepository responsavelRepository, ILoginAcessoRepository loginAcessoRepository, ILoginAcessoRepository loginAcessoRepository1) {
        this.responsavelRepository = responsavelRepository;
        this.loginAcessoRepository = loginAcessoRepository1;
    }

    @Override
    public List<Responsavel> find() {
        log.info(">>>> [ResponsavelService] find iniciado");

        return responsavelRepository.findAll();
    }

    @Override
    public Optional<Responsavel> findById(Long id) {
        log.info(">>>> [ResponsavelService] findById(" + id +") iniciado");

        Optional<Responsavel> responsavel = responsavelRepository.findById(id);

        return Optional.ofNullable(responsavel.orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    @Override
    public Optional<Responsavel> insert(Responsavel obj) {
        log.info(">>>> [ResponsavelService] insert iniciado");

        int loginUser = (int) (Math.random() * 10000);
        int loginSenha = (int) (Math.random() * 10000);

        log.info(">>>> [ResponsavelService] insert Responsavel login: " + loginUser + " senha: " + loginSenha);

        LoginAcesso loginAcesso = new LoginAcesso(Integer.toString(loginUser), Integer.toString(loginSenha));
        loginAcessoRepository.save(loginAcesso);

        obj.setLoginAcesso(loginAcesso);

        return Optional.of(responsavelRepository.save(obj));
    }

    @Override
    public Optional<Responsavel> update(Responsavel obj) {
        log.info(">>>> [ResponsavelService update iniciado]");
        try{
            Responsavel entidade = responsavelRepository.getReferenceById(obj.getId());
            updateData(entidade, obj);
            return Optional.of(responsavelRepository.save(entidade));
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(obj.getId());
        }
    }

    private void updateData(Responsavel entidade, Responsavel obj){
        entidade.setNome(obj.getNome());
        entidade.setApto(obj.getApto());
        entidade.setRg(obj.getRg());
        entidade.setLoginAcesso(obj.getLoginAcesso());
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
        Optional<Responsavel> responsavel = findById(id);
        responsavelRepository.deleteById(id);
    }
}
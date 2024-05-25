package com.argos.argos.service.impl;

import com.argos.argos.model.entities.Administrador;
import com.argos.argos.model.entities.LoginAcesso;
import com.argos.argos.model.entities.Responsavel;
import com.argos.argos.model.repositories.IAdministradorRepository;
import com.argos.argos.model.repositories.ILoginAcessoRepository;
import com.argos.argos.model.repositories.IResponsavelRepository;
import com.argos.argos.service.ILoginAcessoService;
import com.argos.argos.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LoginAcessoService implements ILoginAcessoService {

    private Logger log = LogManager.getLogger(LoginAcessoService.class);
    private final ILoginAcessoRepository loginAcessoRepository;

    private IAdministradorRepository administradorRepository;
    private IResponsavelRepository responsavelRepository;

    public LoginAcessoService(ILoginAcessoRepository loginAcessoRepository, IAdministradorRepository administradorRepository, IResponsavelRepository responsavelRepository) {
        this.loginAcessoRepository = loginAcessoRepository;
        this.administradorRepository = administradorRepository;
        this.responsavelRepository = responsavelRepository;
    }

    @Override
    public Object login(String acessoId, String senha) {
        log.info(">>>> [LoginAcessoService] validateUser(" + acessoId +") iniciado");

        List<Administrador> administradores = administradorRepository.findAll();
        List<Responsavel> responsaveis = responsavelRepository.findAll();

        for (Administrador administrador : administradores) {
            if (administrador.getLoginAcesso().getIdAcesso().equals(acessoId) && administrador.getLoginAcesso().getSenhaAcesso().equals(senha)) {
                return new Object() {
                    public boolean auth = true;
                    public String type = "administrador";
                    public Long userId = administrador.getId();
                };
            }

            for (Responsavel responsavel : responsaveis) {
                if (responsavel.getLoginAcesso().getIdAcesso().equals(acessoId) && responsavel.getLoginAcesso().getSenhaAcesso().equals(senha)) {
                    return new Object() {
                        public final boolean auth = true;
                        public final String type = "responsavel";
                        public final Long userId = responsavel.getId();
                    };
                }
            }
        }

        return new Object() {
            public final boolean auth = false;
        };
    }

    @Override
    public Optional<LoginAcesso> insert(LoginAcesso obj) {
        log.info(">>>> [LoginAcessoService] insert iniciado");

        return Optional.of(loginAcessoRepository.save(obj));
    }

    @Override
    public Optional<LoginAcesso> update(LoginAcesso obj) {
        log.info(">>>> [LoginAcessoService update iniciado]");
        try{
            LoginAcesso entidade = loginAcessoRepository.getReferenceById(obj.getId());
            updateData(entidade, obj);
            return Optional.of(loginAcessoRepository.save(entidade));
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(obj.getId());
        }
    }

    private void updateData(LoginAcesso entidade, LoginAcesso obj){
        entidade.setSenhaAcesso(obj.getSenhaAcesso());
    }

}
package com.argos.argos.config;

import com.argos.argos.model.entities.Administrador;
import com.argos.argos.model.entities.LoginAcesso;
import com.argos.argos.model.entities.Responsavel;
import com.argos.argos.model.repositories.IAdministradorRepository;

import com.argos.argos.model.repositories.ILoginAcessoRepository;
import com.argos.argos.model.repositories.IResponsavelRepository;
import com.argos.argos.service.ILoginAcessoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class LoadDatabase {

    Logger log = LogManager.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(
            IAdministradorRepository administradorRepository,
            ILoginAcessoRepository loginAcessoRepository,
            IResponsavelRepository responsavelRepository
    ) {
        return args -> {
            // Criação e inserção do novo login de acesso
            LoginAcesso loginAcesso1 = new LoginAcesso("admin", "admin");
            loginAcessoRepository.save(loginAcesso1);

            LoginAcesso loginAcesso2 = new LoginAcesso("1234", "user123");
            loginAcessoRepository.saveAll(Arrays.asList(loginAcesso1, loginAcesso2));

            // Criação e inserção do novo administrador
            Administrador administrador1 = new Administrador("Administrador", loginAcesso1);
            administradorRepository.save(administrador1);

            Responsavel responsavel1 = new Responsavel(
                    "Fulano de Tal",
                    "123456789",
                    "Apto 101",
                    loginAcesso2
                    );

            responsavelRepository.save(responsavel1);

            log.info(">>>> [LoadDatabase] dados inseridos no Banco de dados");
        };
    }
}
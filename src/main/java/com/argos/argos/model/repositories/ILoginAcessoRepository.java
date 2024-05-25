package com.argos.argos.model.repositories;

import com.argos.argos.model.entities.LoginAcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoginAcessoRepository extends JpaRepository<LoginAcesso, Long> {

}

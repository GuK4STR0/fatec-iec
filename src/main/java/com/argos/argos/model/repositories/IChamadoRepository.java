package com.argos.argos.model.repositories;

import com.argos.argos.model.entities.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChamadoRepository extends JpaRepository<Chamado, Long> {

}

package com.argos.argos.model.repositories;

import com.argos.argos.model.entities.Trancas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrancasRepository extends JpaRepository<Trancas, Long> {
}

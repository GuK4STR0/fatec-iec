package com.argos.argos.model.repositories;

import com.argos.argos.model.entities.Localidades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILocalidadesRepository extends JpaRepository<Localidades, Long> {

}

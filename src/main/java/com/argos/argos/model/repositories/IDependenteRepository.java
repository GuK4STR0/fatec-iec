package com.argos.argos.model.repositories;

import com.argos.argos.model.entities.Dependente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDependenteRepository extends JpaRepository<Dependente, Long> {


}

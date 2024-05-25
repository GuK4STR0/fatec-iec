package com.argos.argos.model.repositories;

import com.argos.argos.model.entities.TagTranca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITagTrancaRepository extends JpaRepository<TagTranca,Long> {
}

package com.sippulse.pet.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sippulse.pet.entity.Recurso;

public interface RecursoRepository extends JpaRepository<Recurso, Long>{

	List<Recurso> findByIdIn(Set<Long> idsRecursosSolicitados);
}

package com.sippulse.pet.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sippulse.pet.entity.Agendamento;
import com.sippulse.pet.entity.Recurso;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{
	
	/**
	 * 
	 * @param horaInicio
	 * @param horaFim
	 * @return Todos os recursos disponíveis dentre o intervalo especificado
	 */
	@Query(
			"select distinct r from Agendamento a " +
			"	join a.recursos r " + 
			"		where ?1 not between a.dataAgendamento and a.dataTermino " +
			"		and ?2 not between a.dataAgendamento and a.dataTermino")
	List<Recurso> findRecursosLivres(LocalDateTime horaInicio, LocalDateTime horaFim);
	
	/**
	 * 
	 * @param horaInicio
	 * @param horaFim
	 * @param recursos
	 * @return Os recursos ocupados dentre os recursos solicitados
	 */
	@Query(
			"select distinct r from Agendamento a " +
			"	join a.recursos r " + 
			"		where ?1 between a.dataAgendamento and a.dataTermino " +
			"		or ?2 between a.dataAgendamento and a.dataTermino " +
			"		and r.id IN ?3")
	List<Recurso> findRecursosOcupados(LocalDateTime horaInicio, LocalDateTime horaFim, Set<Long> recursos);

	@Query(
			"select a from Agendamento a " +
			"	join a.recursos r " + 
			"		where " +
			"		(?1 is null or r.nome = ?1) " +
			"	and (?2 is null "
			+ "		or (day(?2) between day(a.dataAgendamento) and day(a.dataTermino) "
			+ "			and year(?2) between year(a.dataAgendamento) and year(a.dataTermino)	"
			+ "			and day(?2) between month(a.dataAgendamento) and month(a.dataTermino)))	")
	Page<Agendamento> findByFilter(String nomeRecurso, String dia, Pageable pageable);
}

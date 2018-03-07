package com.oapc.repo;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oapc.model.Pdu;

public interface PduRepository extends JpaRepository<Pdu, Long> {

	@Query("select p from Pdu p")
	List<Pdu> findAllList();
	
	@Query("select p from Pdu p order by id")
	Stream<Pdu> findAllStream();
	
}
package com.oapc.repo;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oapc.model.Note;
import com.oapc.model.Producte;

public interface ProductRepository extends JpaRepository<Producte, Long> {
	
	@Query("select n from Producte n")
	List<Producte> findAllList();
	
	@Query("select n from Producte n")
	Stream<Producte> findAllStream();
	
	@Query("select n from Producte n where id = ?1")
	Producte findById(Long id);
}
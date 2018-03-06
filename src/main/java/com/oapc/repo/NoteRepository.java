package com.oapc.repo;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oapc.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

	@Query("select n from Note n")
	List<Note> findAllList();
	
	@Query("select n from Note n")
	Stream<Note> findAllStream();
	
	@Query("select n from Note n where id = ?1")
	Note findById(Long id);
}
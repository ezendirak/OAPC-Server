package com.oapc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oapc.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
package com.macrosoftas.archijee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.macrosoftas.archijee.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
	public Genre findByCode(String code);
}

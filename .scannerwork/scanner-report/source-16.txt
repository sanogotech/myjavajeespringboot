package com.macrosoftas.archijee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.macrosoftas.archijee.model.Livre;

public interface LivreRepository extends JpaRepository<Livre, Long> {

}

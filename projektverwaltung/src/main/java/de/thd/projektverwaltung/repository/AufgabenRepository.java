package de.thd.projektverwaltung.repository;

import de.thd.projektverwaltung.model.Aufgabe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AufgabenRepository extends JpaRepository<Aufgabe, Long> {

}


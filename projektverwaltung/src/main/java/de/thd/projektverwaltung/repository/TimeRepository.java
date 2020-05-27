package de.thd.projektverwaltung.repository;

import de.thd.projektverwaltung.model.Time;
import de.thd.projektverwaltung.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {

    Time findByAufgabe(Aufgabe aufgabe);
}

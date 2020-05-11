package de.thd.projektverwaltung.repository;

import de.thd.projektverwaltung.model.Projekt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjektRepository extends JpaRepository<Projekt, Long> {
    Projekt findByPid(int pid);


}

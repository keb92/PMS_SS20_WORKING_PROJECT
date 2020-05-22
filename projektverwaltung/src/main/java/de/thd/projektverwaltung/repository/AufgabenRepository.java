package de.thd.projektverwaltung.repository;
import de.thd.projektverwaltung.model.Aufgabe;
import de.thd.projektverwaltung.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AufgabenRepository extends CrudRepository<Aufgabe, Long> {

    Aufgabe findByaid(int a_id);
    Aufgabe findByUser(User user);
}


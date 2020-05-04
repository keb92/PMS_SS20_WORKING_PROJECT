package de.thd.projektverwaltung.repository;

import de.thd.projektverwaltung.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

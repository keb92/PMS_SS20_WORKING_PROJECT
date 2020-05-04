package de.thd.projektverwaltung.service;

import de.thd.projektverwaltung.model.Customer;
import de.thd.projektverwaltung.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public  CustomerService (CustomerRepository customerRepository) {this.customerRepository = customerRepository; }

    public Customer saveCustomer(Customer customer) {

        customer.setKundenname(customer.getKundenname());
        return customerRepository.save(customer);
    }



}

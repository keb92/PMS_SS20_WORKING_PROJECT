package de.thd.projektverwaltung.service;

import de.thd.projektverwaltung.model.Customer;
import de.thd.projektverwaltung.model.Projekt;
import de.thd.projektverwaltung.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public  CustomerService (CustomerRepository customerRepository) {this.customerRepository = customerRepository; }

    public Customer saveCustomer(Customer customer) {

        customer.setKundenname(customer.getKundenname());
        return customerRepository.save(customer);
    }
    public List<Customer> getAllCustomers() {
        List<Customer> result = (List<Customer>) customerRepository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Customer>();
        }
    }





}

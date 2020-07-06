package de.thd.projektverwaltung.service;

import de.thd.projektverwaltung.model.Customer;
import de.thd.projektverwaltung.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The Data Access Class of {@link Customer} objects. All Interactions with the database regarding the entity
 * {@link Customer} should be handled by methods provided by this class.
 */
@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public  CustomerService (CustomerRepository customerRepository) {this.customerRepository = customerRepository; }

    /**
     * Creates a new {@link Customer} and saves it into the Database
     * @param customer
     * @return
     */
    public Customer saveCustomer(Customer customer) {

        customer.setKundenname(customer.getKundenname());
        return customerRepository.save(customer);
    }

    /**
     * Returns all {@link Customer} from the Database
     * @return all Customer
     */
    public List<Customer> getAllCustomers() {
        List<Customer> result = (List<Customer>) customerRepository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Customer>();
        }
    }





}

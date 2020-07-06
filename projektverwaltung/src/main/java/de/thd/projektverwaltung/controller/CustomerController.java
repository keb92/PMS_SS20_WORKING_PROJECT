package de.thd.projektverwaltung.controller;

import de.thd.projektverwaltung.model.Customer;
import de.thd.projektverwaltung.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * <p> Provide Form for {@link Customer} </p>
     *
     * <p> expect HTTP GET and request '/admin/createCustomer' </p>
     * @return
     */
    @GetMapping(value="admin/createCustomer")
    public ModelAndView show(){
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = new Customer();
        modelAndView.addObject( "customer", customer);
        modelAndView.setViewName("/admin/createCustomer");
        return modelAndView;

    }

    /**
     * <p> Save a {@link Customer} </p>
     *
     * <p> expect HTTP POST and request 'admin/createCustomer' </p>
     * @param customer
     * @param bindingResult
     * @return
     */
    @PostMapping(value="admin/createCustomer")
    public ModelAndView createCustomer(@Valid Customer customer, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        customerService.saveCustomer(customer);
        modelAndView.addObject("successMessage","Kunde erfolgreich angelegt!");
        modelAndView.addObject("customer", new Customer());
        modelAndView.setViewName("/admin/createCustomer");
        return modelAndView;

    }

    /**
     * <p> Search for all {@link Customer} and add as Object to modelAndView </p>
     *
     * <p> expect HTTP GET and request 'admin/showCustomers' </p>
     * @return
     */
    @GetMapping(value="admin/showCustomers")
    public ModelAndView sumCustomer(){
        ModelAndView modelAndView = new ModelAndView();
        List<Customer> list = customerService.getAllCustomers();
        modelAndView.addObject("customers", list);
        modelAndView.setViewName("/admin/showCustomer");

        return modelAndView;

    }
}

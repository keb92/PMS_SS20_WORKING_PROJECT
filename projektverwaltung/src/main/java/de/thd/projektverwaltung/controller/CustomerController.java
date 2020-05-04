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

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value="admin/createCustomer")
    public ModelAndView show(){
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = new Customer();
        modelAndView.addObject( "customer", customer);
        modelAndView.setViewName("/admin/createCustomer");
        return modelAndView;

    }

    @PostMapping(value="admin/createCustomer")
    public ModelAndView createCustomer(@Valid Customer customer, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        customerService.saveCustomer(customer);
        modelAndView.addObject("successMessage","Kunde erfolgreich angelegt!");
        modelAndView.addObject("customer", new Customer());
        modelAndView.setViewName("/admin/createCustomer");
        return modelAndView;

    }

}

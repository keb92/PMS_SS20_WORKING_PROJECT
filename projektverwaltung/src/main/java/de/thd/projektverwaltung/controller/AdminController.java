package de.thd.projektverwaltung.controller;

import de.thd.projektverwaltung.model.Customer;
import de.thd.projektverwaltung.model.Projekt;

import de.thd.projektverwaltung.model.User;
import de.thd.projektverwaltung.service.CustomerService;
import de.thd.projektverwaltung.service.ProjektService;
import de.thd.projektverwaltung.repository.UserRepository;
import de.thd.projektverwaltung.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

@Controller
public class AdminController<userRepository> {

    @Autowired
    private ProjektService projektservice;
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = {"/admin/createProject"})
    public ModelAndView project(ModelAndView modelAndView) {
        Projekt projekt = new Projekt();
        List<Customer> list = customerService.getAllCustomers();
        modelAndView.addObject("customers",list);
        modelAndView.addObject("projekt", projekt);
        modelAndView.setViewName("admin/createprojects");
        return modelAndView;
    }

    @PostMapping(value = {"/admin/createProject"})
    public ModelAndView saveit(@Valid Projekt projekt,  BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        projektservice.saveProjekt(projekt);
        modelAndView.addObject("successMessage", "Projekt erfolgreich anglegt.");
        modelAndView.addObject("projekt", new Projekt());
        modelAndView.setViewName("admin/createprojects");
        return modelAndView;
    }


}

package de.thd.projektverwaltung.controller;

import de.thd.projektverwaltung.model.Customer;
import de.thd.projektverwaltung.model.Projekt;
import de.thd.projektverwaltung.service.CustomerService;
import de.thd.projektverwaltung.service.ProjektService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController{

    @Autowired
    private ProjektService projektservice;
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = {"/admin/createProject"})
    public ModelAndView createProject(ModelAndView modelAndView) {
        Projekt projekt = new Projekt();
        List<Customer> list = customerService.getAllCustomers();
        modelAndView.addObject("Customers",list);
        modelAndView.addObject("projekt", projekt);
        modelAndView.setViewName("admin/createprojects");
        return modelAndView;
    }

    @PostMapping(value = {"/admin/createProject"})
    public ModelAndView saveProject(@ModelAttribute Projekt projekt) {
        ModelAndView modelAndView = new ModelAndView();
        projektservice.saveProjekt(projekt);
        modelAndView.addObject("successMessage", "Projekt erfolgreich anglegt.");
        modelAndView.addObject("projekt", new Projekt());
        modelAndView.setViewName("admin/createprojects");
        return modelAndView;
    }


}

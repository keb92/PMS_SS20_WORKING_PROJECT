package de.thd.projektverwaltung.controller;

import de.thd.projektverwaltung.model.Projekt;

import de.thd.projektverwaltung.service.ProjektService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import de.thd.projektverwaltung.model.Projekt;

import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class AdminController {

    @Autowired
    private ProjektService projektservice;


    @GetMapping(value = {"/admin/createProject"})
    public ModelAndView project() {
        ModelAndView modelAndView = new ModelAndView();
        Projekt projekt = new Projekt();
        modelAndView.addObject("projekt", projekt);
        modelAndView.setViewName("admin/createprojects");
        return modelAndView;
    }

    @PostMapping(value = {"/admin/createProject"})
    public ModelAndView create(@Valid Projekt projekt,  BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        projektservice.saveProjekt(projekt);
        modelAndView.addObject("successMessage", "Projekt erfolgreich anglegt.");
        modelAndView.addObject("projekt", new Projekt());
        modelAndView.setViewName("/admin/home");


        return modelAndView;
    }




}

package de.thd.projektverwaltung.controller;

import de.thd.projektverwaltung.model.Projekt;

import de.thd.projektverwaltung.model.User;
import de.thd.projektverwaltung.service.ProjektService;
import de.thd.projektverwaltung.repository.UserRepository;
import de.thd.projektverwaltung.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

@Controller
public class AdminController<userRepository> {

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

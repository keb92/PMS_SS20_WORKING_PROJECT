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

@Controller
public class EmployeeController {
    @Autowired
    private ProjektService projektService;

    @GetMapping(value = {"/mitarbeiter/recordTime"})
    public ModelAndView saveit(@Valid Projekt projekt,  BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        List<Projekt> list = projektService.getAllProjekt();
        modelAndView.addObject("projects",list);
        modelAndView.addObject("successMessage", "Zeit erfolgreich erfasst");
        modelAndView.setViewName("/mitarbeiter/recordTime");
        return modelAndView;
    }

}

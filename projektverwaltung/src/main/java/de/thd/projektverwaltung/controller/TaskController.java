package de.thd.projektverwaltung.controller;


import de.thd.projektverwaltung.model.Aufgabe;
import de.thd.projektverwaltung.repository.AufgabenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class TaskController {

    @Autowired
    private AufgabenRepository aufgabenRepository;

    @GetMapping(value = "/admin/createTask")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView();
        Aufgabe aufgabe = new Aufgabe();
        modelAndView.addObject( "aufgabe",aufgabe);
        modelAndView.setViewName("/admin/createTask");
        return modelAndView;

    }

    @PostMapping(value = "/admin/createTask")
    public ModelAndView saveit(@Valid Aufgabe aufgabe, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        aufgabenRepository.save(aufgabe);
        modelAndView.addObject("aufgabe", new Aufgabe());
        modelAndView.setViewName("/admin/createTask");
        return modelAndView;


    }

}

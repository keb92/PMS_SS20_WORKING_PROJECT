package de.thd.projektverwaltung.controller;


import de.thd.projektverwaltung.model.*;
import de.thd.projektverwaltung.repository.AufgabenRepository;
import de.thd.projektverwaltung.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private AufgabenService aufgabenService;
    @Autowired
    private ProjektService projektService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;
    @Autowired
    private AufgabenRepository aufgabenRepository;


    @GetMapping(value = "/admin/createTask")
    public ModelAndView createProject(ModelAndView modelAndView) {
        Aufgabe aufgabe = new Aufgabe();
        List<Projekt> pro = projektService.getAllProjekt();
        modelAndView.addObject("Projects",pro);
        modelAndView.addObject("aufgabe", aufgabe);
        modelAndView.setViewName("admin/createTask");
        return modelAndView;
    }

    @PostMapping(value = "/admin/createTask")
    public ModelAndView saveTask(@ModelAttribute Aufgabe aufgabe, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if( aufgabenService.checkFitting(aufgabe) == false){
            bindingResult
                    .rejectValue("aufwand", "error.aufwand",
                            "Das Projektbudget würde überschritten - bitte kleinere Aufgabe planen oder mit Kunden abklären!");
            List<Projekt> pro = projektService.getAllProjekt();
            modelAndView.addObject("Projects",pro);
            modelAndView.setViewName("admin/createTask");
        }
        else {
            aufgabenService.saveAufgabe(aufgabe);
            modelAndView.addObject("successMessage", "Aufgabe erfolgreich anglegt.");
            modelAndView.addObject("aufgabe", new Aufgabe());
            modelAndView.setViewName("admin/createTask");
        }
        return modelAndView;
    }

    @GetMapping(value = "/admin/tasktouser")
    public ModelAndView taskToUser(ModelAndView modelAndView) {
        Aufgabe aufgabe = new Aufgabe();
        List<User> user = userService.getAllUsers();
        List<Aufgabe> aufg = aufgabenService.getAllAufgaben();
        modelAndView.addObject("Tasks", aufg);
        modelAndView.addObject("Users", user);
        modelAndView.addObject("aufgabe", aufgabe);
        modelAndView.setViewName("admin/tasktouser");
        return modelAndView;
    }

    @PostMapping(value = "/admin/tasktouser")
    public ModelAndView saveUser(@ModelAttribute Aufgabe aufgabe) {
        aufgabenRepository.save(aufgabe);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("successMessage", "Aufgabe erfolgreich zugewiesen.");
        modelAndView.addObject("aufgabe", new Aufgabe());
        modelAndView.setViewName("admin/tasktouser");
        return modelAndView;
    }

}

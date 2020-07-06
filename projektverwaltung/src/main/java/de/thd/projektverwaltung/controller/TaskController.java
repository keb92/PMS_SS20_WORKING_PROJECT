package de.thd.projektverwaltung.controller;

import de.thd.projektverwaltung.model.*;
import de.thd.projektverwaltung.repository.AufgabenRepository;
import de.thd.projektverwaltung.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    /**
     * <p> Provide Form for {@link Aufgabe} </p>
     * <p> Search for all {@link Projekt}</p>
     *
     * <p> expect HTTP GET and request '/admin/createTask' </p>
     * @param modelAndView
     * @return
     */
    @GetMapping(value = "/admin/createTask")
    public ModelAndView createTask(ModelAndView modelAndView) {
        Aufgabe aufgabe = new Aufgabe();
        List<Projekt> pro = projektService.getAllProjekt();
        modelAndView.addObject("Projects",pro);
        modelAndView.addObject("aufgabe", aufgabe);
        modelAndView.setViewName("admin/createTask");
        return modelAndView;
    }

    /**
     * <p> Saves a {@link Aufgabe}</p>
     * <p> if aufwand for {@link Aufgabe} > budget for {@link Projekt} the value will be rejected </p>
     *
     * <p> expect HTTP POST and request '/admin/createTask' </p>
     * @param aufgabe
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/admin/createTask")
    public ModelAndView saveTask(@ModelAttribute Aufgabe aufgabe, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        aufgabe.setUser(user);
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

    /**
     * <p> Form to Assign {@link Aufgabe} to {@link User} </p>
     * <p> Search for all {@link Aufgabe} and {@link User} </p>
     *
     * <p> expect HTTP GET and request '/admin/tasktouser'</p>
     * @param modelAndView
     * @return
     */
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

    /**
     * <p> Saves a {@link Aufgabe} assigned to a {@link User} </p>
     *
     * <p> expect HTTP POST and request '/admin/tasktouser' </p>
     * @param aufgabe
     * @return
     */
    @PostMapping(value = "/admin/tasktouser")
    public ModelAndView saveUser(@ModelAttribute Aufgabe aufgabe) {
        aufgabe.setAufwand(aufgabe.getAufwand());
        aufgabenRepository.save(aufgabe);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("successMessage", "Aufgabe erfolgreich zugewiesen.");
        modelAndView.addObject("aufgabe", new Aufgabe());
        modelAndView.setViewName("admin/tasktouser");
        return modelAndView;
    }

}

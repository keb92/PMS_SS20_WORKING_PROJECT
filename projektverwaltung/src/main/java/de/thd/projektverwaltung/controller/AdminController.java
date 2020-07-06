package de.thd.projektverwaltung.controller;

import de.thd.projektverwaltung.model.Aufgabe;
import de.thd.projektverwaltung.model.Customer;
import de.thd.projektverwaltung.model.Projekt;
import de.thd.projektverwaltung.model.User;
import de.thd.projektverwaltung.service.*;
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
    @Autowired
    private UserService userService;
    @Autowired
    private AufgabenService aufgabenService;
    @Autowired
    private TimeService timeService;

    /**
     * <p> Provide Form for {@link Projekt} and search for {@link User} and {@link Customer} </p>
     *
     * <p> expect HTTP GET and request '/admin/createProject'</p>
     * @param modelAndView
     * @return
     */
    @GetMapping(value = {"/admin/createProject"})
    public ModelAndView createProject(ModelAndView modelAndView) {
        Projekt projekt = new Projekt();
        List<User> users = userService.getAllUsers();
        List<Customer> list = customerService.getAllCustomers();
        modelAndView.addObject("Users", users);
        modelAndView.addObject("Customers",list);
        modelAndView.addObject("projekt", projekt);
        modelAndView.setViewName("admin/createprojects");
        return modelAndView;
    }

    /**
     * <p> Search for all {@link Projekt} and add as Object to modelAndView  </p>
     *
     * <p> expect HTTP GET and request '/admin/showProject'</p>
     * @param modelAndView
     * @return
     */
    @GetMapping(value = {"/admin/showProject"})
    public ModelAndView showProjects(ModelAndView modelAndView){
        List<Projekt> projekte = projektservice.getAllProjekt();
        modelAndView.addObject("projekt", projekte);
        modelAndView.setViewName("admin/showProjekt");

        return modelAndView;
    }

    /**
     * <p> Search for all {@link Aufgabe} and add as Object to modelAndView </p>
     *
     * <p> expect HTTP GET and request '/admin/showTask' </p>
     * @param modelAndView
     * @return
     */
    @GetMapping(value = {"/admin/showTask"})
    public ModelAndView showTask(ModelAndView modelAndView){
        List<Aufgabe> aufgaben = aufgabenService.getAllAufgaben();
        List<Aufgabe> list2 = timeService.returnAufgaben();
        modelAndView.addObject("aufgabe", aufgaben);
        modelAndView.setViewName("admin/showTask");

        return modelAndView;
    }

    /**
     * <p> Saves a {@link Projekt} based on input of List </p>
     *
     * <p> expect HTTP POST and request '/admin/createProject' </p>
     * @param projekt
     * @return
     */
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

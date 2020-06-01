package de.thd.projektverwaltung.controller;

import de.thd.projektverwaltung.model.Aufgabe;
import de.thd.projektverwaltung.model.*;
import de.thd.projektverwaltung.model.User;
import de.thd.projektverwaltung.service.AufgabenService;
import de.thd.projektverwaltung.service.TimeService;
import de.thd.projektverwaltung.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private AufgabenService aufgabenService;
    @Autowired
    private TimeService timeService;



    @GetMapping(value={"/", "/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        userService.kapaChange();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @GetMapping(value="/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "Es ist bereits ein Mitarbeiter mit dem angegebenen Benutzernamen registriert");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "Mitarbeiter erfolgreich anglegt.");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }




    @GetMapping(value="/admin/home")
    public ModelAndView admin(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        List<User> list = userService.getAllUsers();
        for(int i = 0;i<list.size();i++){
            list.get(i).setZeitkonto(list.get(i).getZeitkonto()-timeService.countTime(list.get(i)));
        }
        modelAndView.addObject("userName", "Hallo " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("users",list);
        modelAndView.addObject("userid",user.getId());
        modelAndView.addObject("kapa",user.getZeitkonto());
        modelAndView.addObject("adminMessage","Teamleiterbereich!!");
        modelAndView.setViewName("/admin/home");
        return modelAndView;
    }


    @GetMapping(value="/mitarbeiter/home")
    public ModelAndView mitarbeiter(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        int gebucht = timeService.countTime(user);
        List<Aufgabe> list = aufgabenService.getAllAufgaben();
        List<Aufgabe> list2 = timeService.returnAufgaben();
        modelAndView.addObject("userName", "Hallo " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("employeeMessage","Mitarbeiterbereich!!");
        modelAndView.addObject("aufgaben",list);
        modelAndView.addObject("timeaufgaben",list2);
        modelAndView.addObject("kapa",user.getZeitkonto() - gebucht);
        modelAndView.addObject("userid",user.getId());
        modelAndView.setViewName("/mitarbeiter/home");
        return modelAndView;
    }


}

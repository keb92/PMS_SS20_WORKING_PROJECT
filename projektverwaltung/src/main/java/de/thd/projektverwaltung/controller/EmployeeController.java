package de.thd.projektverwaltung.controller;

import de.thd.projektverwaltung.model.Aufgabe;
import de.thd.projektverwaltung.model.Time;
import de.thd.projektverwaltung.model.User;
import de.thd.projektverwaltung.service.AufgabenService;
import de.thd.projektverwaltung.service.ProjektService;
import de.thd.projektverwaltung.service.TimeService;
import de.thd.projektverwaltung.service.UserService;
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
public class EmployeeController {
    @Autowired
    private ProjektService projektService;
    @Autowired
    private AufgabenService aufgabenService;
    @Autowired
    private TimeService timeService;
    @Autowired
    private UserService userService;

    /**
     * <p> Provide Form to record {@link Time} for a {@link Aufgabe}</p>
     * <p> Searches for all {@link Aufgabe} assigned to a {@link User}</p>
     *
     * <p> expect HTTP GET and request '/mitarbeiter/recordTime' </p>
     * @param modelAndView
     * @return
     */
    @GetMapping(value = {"/mitarbeiter/recordTime"})
    public ModelAndView saveit(ModelAndView modelAndView) {
        Time time = new Time();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        List<Aufgabe> list = aufgabenService.getAllAufgaben();
        modelAndView.addObject("projects",list);
        modelAndView.addObject("time",time);
        modelAndView.addObject("userid",user.getId());
        modelAndView.setViewName("/mitarbeiter/recordTime");
        return modelAndView;
    }

    /**
     * <p> Saves a {@link Time} for a {@link Aufgabe} assigned to a {@link User} </p>
     * <p> If Input Time of {@link User} > Aufwand of {@link Aufgabe} the value will be rejected </p>
     *
     * <p> expect HTTP POST and request '/mitarbeiter/recordTime' </p>
     * @param time
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/mitarbeiter/recordTime")
    public ModelAndView saveTime(@ModelAttribute Time time, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (timeService.countMax(time) == false){
            bindingResult
                    .rejectValue("zeit", "error.zeit",
                            "Das Aufgabenbudget würde überschritten - bitte weniger Zeit buchen oder mit Projektleiter abklären");
            List<Aufgabe> list = aufgabenService.getAllAufgaben();
            modelAndView.addObject("projects",list);
            modelAndView.setViewName("/mitarbeiter/recordTime");
        }

        else{
        timeService.saveTime(time);
        modelAndView.addObject("successMessage", "Zeit erfolgreich gebucht.");
        modelAndView.addObject("time", new Time());
        List<Aufgabe> list = aufgabenService.getAllAufgaben();
        modelAndView.addObject("projects",list);
        modelAndView.setViewName("mitarbeiter/recordTime");}
        return modelAndView;
    }

}
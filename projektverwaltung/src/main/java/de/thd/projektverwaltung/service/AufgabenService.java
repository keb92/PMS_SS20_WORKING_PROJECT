package de.thd.projektverwaltung.service;
import de.thd.projektverwaltung.model.Aufgabe;
import de.thd.projektverwaltung.model.Projekt;
import de.thd.projektverwaltung.model.Customer;
import de.thd.projektverwaltung.model.User;
import de.thd.projektverwaltung.repository.AufgabenRepository;
import de.thd.projektverwaltung.repository.ProjektRepository;
import de.thd.projektverwaltung.repository.UserRepository;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class AufgabenService {

    private AufgabenRepository aufgabenRepository;
    private UserRepository userRepository;
    private ProjektRepository projektRepository;

    @Autowired
    public AufgabenService (AufgabenRepository aufgabenRepository){ this.aufgabenRepository = aufgabenRepository; }

    public Aufgabe saveAufgabe(Aufgabe aufgabe){


        aufgabe.setBeschreibung(aufgabe.getBeschreibung());
        aufgabe.setAufwand(aufgabe.getAufwand());


        return aufgabenRepository.save(aufgabe);

    }

    public List<Aufgabe> getAllAufgaben() {
        List<Aufgabe> result = (List<Aufgabe>) aufgabenRepository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Aufgabe>();
        }
    }
    /*public Aufgabe updateAufgabe(Aufgabe fromIns){
        Aufgabe uAufgabe = aufgabenRepository.findByaid(fromIns.getAid());
        uAufgabe.setAufwand(fromIns.getAufwand());
        aufgabenRepository.save(uAufgabe);
        return uAufgabe;
    }*/

    public Aufgabe findByaid(int a_id) {
        return aufgabenRepository.findByaid(a_id);
    }
    public Aufgabe findByUser (User user) {return aufgabenRepository.findByUser(user);}

    public List<Aufgabe> getUserAufgabe(User user){
        List<Aufgabe> result = (List<Aufgabe>) aufgabenRepository.findByUser(user);
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Aufgabe>();
        }
    }




}

package de.thd.projektverwaltung.service;
import de.thd.projektverwaltung.model.Aufgabe;
import de.thd.projektverwaltung.model.Projekt;
import de.thd.projektverwaltung.model.User;
import de.thd.projektverwaltung.repository.AufgabenRepository;
import de.thd.projektverwaltung.repository.ProjektRepository;
import de.thd.projektverwaltung.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AufgabenService {

    private AufgabenRepository aufgabenRepository;
    private UserRepository userRepository;

    @Autowired
    private ProjektRepository projektRepository;

    @Autowired
    private ProjektService projektService;

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

    public boolean checkFitting (Aufgabe aufgabe){
        List<Aufgabe> aufgaben = aufgabenRepository.findAll();
        List<Projekt> projekte = projektRepository.findAll();
        int apid = aufgabe.getProjekt().getPid();
        System.out.println("APID:" + apid);
        System.out.println("Projekte: " + projekte);
        System.out.println("Aufgaben: " + aufgaben);
        int maximum = 0;
        int zuvergleichen = aufgabe.getAufwand();
        for (int i = 0; i<projekte.size();i++){
            if(apid == projekte.get(i).getPid()){
                maximum = maximum + projekte.get(i).getBudget();
            }
        }
        System.out.println("Maximum:" + maximum);
        for (int j = 0; j<aufgaben.size();j++){
            if(apid == aufgaben.get(j).getProjekt().getPid()){
                zuvergleichen = zuvergleichen + aufgaben.get(j).getAufwand();
            }
        }
        System.out.println("zu vergleichen::" + zuvergleichen);
        if (zuvergleichen <= maximum){
            return true;
        }
        else{
            return false;
        }

    }
    /*public Aufgabe updateAufgabe(Aufgabe fromIns){
        Aufgabe uAufgabe = aufgabenRepository.findByaid(fromIns.getAid());
        uAufgabe.setAufwand(fromIns.getAufwand());
        aufgabenRepository.save(uAufgabe);
        return uAufgabe;
    }*/

    /*public Aufgabe findByaid(int a_id) {
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
    }*/




}

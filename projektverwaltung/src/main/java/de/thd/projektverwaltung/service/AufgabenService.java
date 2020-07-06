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

/**
 * The Data Access Class of {@link Aufgabe} objects. All Interactions with the database regarding the entity
 * {@link Aufgabe} should be handled by methods provided by this class.
 */
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

    /**
     * Creates a new {@link Aufgabe} and saves it into the Database.
     * @param aufgabe
     * @return
     */
    public Aufgabe saveAufgabe(Aufgabe aufgabe){
        aufgabe.setBeschreibung(aufgabe.getBeschreibung());
        aufgabe.setAufwand(aufgabe.getAufwand());

        return aufgabenRepository.save(aufgabe);
    }

    /**
     * Returns all {@link Aufgabe} from the Database
     * @return all Aufgabe
     */
    public List<Aufgabe> getAllAufgaben() {
        List<Aufgabe> result = (List<Aufgabe>) aufgabenRepository.findAll();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Aufgabe>();
        }
    }

    /**
     * Check if Aufwand of a {@link Aufgabe} > or < a Budget of {@link Projekt}
     * @param aufgabe
     * @return true or false
     */
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
}

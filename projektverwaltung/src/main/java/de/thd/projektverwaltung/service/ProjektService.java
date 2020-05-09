package de.thd.projektverwaltung.service;
import de.thd.projektverwaltung.model.Projekt;
import de.thd.projektverwaltung.model.Customer;
import de.thd.projektverwaltung.model.User;
import de.thd.projektverwaltung.repository.ProjektRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.HashSet;

@Service
public class ProjektService {

    private ProjektRepository projektrepository;

    @Autowired
    public ProjektService (ProjektRepository projektRepository){
        this.projektrepository = projektRepository;
    }

    public Projekt saveProjekt(Projekt projekt) {

        /*
        projekt.setBezeichnung(projekt.getBezeichnung());
        projekt.setBudget(projekt.getBudget());
        projekt.setScope(projekt.getScope());
        projekt.setCustomers();
        */
        return projektrepository.save(projekt);
    }
    public Projekt findByPid(int pid) {
        return projektrepository.findByPid(pid);
    }

}

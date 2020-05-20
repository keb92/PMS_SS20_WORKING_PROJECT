package de.thd.projektverwaltung.service;
import de.thd.projektverwaltung.model.Projekt;
import de.thd.projektverwaltung.model.Customer;
import de.thd.projektverwaltung.model.User;
import de.thd.projektverwaltung.repository.ProjektRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
        */
        return projektrepository.save(projekt);
    }
    public Projekt findByPid(int pid) {
        return projektrepository.findByPid(pid);
    }

    public List<Projekt> getAllProjekt()
    {
        List<Projekt> result = (List<Projekt>) projektrepository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Projekt>();
        }
    }


}

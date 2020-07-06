package de.thd.projektverwaltung.service;

import de.thd.projektverwaltung.model.Projekt;
import de.thd.projektverwaltung.repository.ProjektRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * The Data Access Class of {@link Projekt} objects. All Interactions with the database regarding the entity
 * {@link Projekt} should be handled by methods provided by this class.
 */
@Service
public class ProjektService {

    private ProjektRepository projektrepository;

    @Autowired
    public ProjektService (ProjektRepository projektRepository){
        this.projektrepository = projektRepository;
    }

    /**
     * Saves new {@link Projekt} into the Database.
     * @param projekt
     * @return
     */
    public Projekt saveProjekt(Projekt projekt) {

        return projektrepository.save(projekt);
    }

    /**
     * Returns a single Projekt by its primary database key.
     * @param pid the primary key of a {@link Projekt}
     * @return a single Projekt
     */
    public Projekt findByPid(int pid) {

        return projektrepository.findByPid(pid);
    }

    /**
     * Returns all {@link Projekt} from the database.
     * @return all Projekt
     */
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

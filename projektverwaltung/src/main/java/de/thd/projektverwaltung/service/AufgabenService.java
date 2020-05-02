package de.thd.projektverwaltung.service;


import de.thd.projektverwaltung.model.Aufgabe;
import de.thd.projektverwaltung.repository.AufgabenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AufgabenService {
    private AufgabenRepository aufgabenRepository;

    @Autowired
    public AufgabenService (AufgabenRepository aufgabenRepository) {this.aufgabenRepository = aufgabenRepository; }

    public Aufgabe saveAufgabe(Aufgabe aufgabe){

        aufgabe.setAufgabe(aufgabe.getAufgabe());
        aufgabe.setAufwand(aufgabe.getAufwand());
        return aufgabenRepository.save(aufgabe);

    }



}

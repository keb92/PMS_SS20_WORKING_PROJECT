package de.thd.projektverwaltung.service;
import de.thd.projektverwaltung.model.Time;
import de.thd.projektverwaltung.model.*;
import de.thd.projektverwaltung.repository.AufgabenRepository;
import de.thd.projektverwaltung.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeService {

    private TimeRepository timeRepository;
    private TimeService timeService;

    @Autowired
    private AufgabenRepository aufgabenRepository;

    @Autowired
    public AufgabenService aufgabenService;

    @Autowired
    public TimeService (TimeRepository timeRepository){
        this.timeRepository = timeRepository;
    }


    public List<Aufgabe> returnAufgaben(){
        List<Aufgabe> aufgaben = aufgabenRepository.findAll();
        List<Time> times = timeRepository.findAll();

        for (int i = 0; i<aufgaben.size();i++){
            for (int j = 0; j<times.size(); j++){
                if (aufgaben.get(i).getAid() == times.get(j).getAufgabe().getAid()){
                    aufgaben.get(i).setAufwand(aufgaben.get(i).getAufwand()-times.get(j).getZeit());
                }
            }
        }
        return aufgaben;
    }

    public int countTime(User user){
        int userid = user.getId();
        List<Aufgabe> aufgaben = aufgabenRepository.findAll();
        List<Integer> user_aufgaben = new ArrayList<>();
        int index = 0;
        for (int i = 0; i<aufgaben.size();i++){
            if (userid == aufgaben.get(i).getUser().getId()){
                user_aufgaben.add(0,aufgaben.get(i).getAid());
                index++;
            }
        }
        List<Time> time_user = timeRepository.findAll();
        int counter = 0;
        for (int j = 0; j<time_user.size();j++){
            for (int k = 0; k<user_aufgaben.size();k++){
                if (user_aufgaben.get(k) == time_user.get(j).getAufgabe().getAid()){
                    counter = counter + time_user.get(j).getZeit();
                }
            }
        }
        return counter;

    }

    public Time saveTime(Time time) {
        List<Time> times = timeRepository.findAll();
        int counter = time.getZeit();
        int aufgabenid = time.getAufgabe().getAid();

        for (int i = 0; i < times.size();i++){
            if (aufgabenid == times.get(i).getAufgabe().getAid()){
                counter = counter + times.get(i).getZeit();
                time.setTid(times.get(i).getTid());
            }
            else{
                counter = counter;
            }
        }
        time.setZeit(counter);
        return timeRepository.save(time);
    }

    public boolean countMax(Time time){
        List<Time> times = timeRepository.findAll();
        List<Aufgabe> aufgaben = aufgabenRepository.findAll();
        int counter = time.getZeit();
        int aufgabenid = time.getAufgabe().getAid();
        int vergleich = 0;
        for (int j = 0; j < aufgaben.size();j++){
            if (aufgabenid == aufgaben.get(j).getAid()){
            System.out.println(aufgaben.get(j).getAufwand());
            vergleich = vergleich + aufgaben.get(j).getAufwand();}
        }
        for (int i = 0; i < times.size();i++){
            if (aufgabenid == times.get(i).getAufgabe().getAid()){
                counter = counter + times.get(i).getZeit();
            }
        }

        if (counter > vergleich){
            return false;
        }
        else {
            return true;
        }
    }


    public List<Time> getAllTime() {
        List<Time> result = (List<Time>) timeRepository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Time>();
        }
    }
    public Time findByAufgabe(Aufgabe aufgabe){return timeRepository.findByAufgabe(aufgabe);}



}

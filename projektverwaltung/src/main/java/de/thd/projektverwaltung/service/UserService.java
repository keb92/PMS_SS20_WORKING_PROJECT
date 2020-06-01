package de.thd.projektverwaltung.service;

import de.thd.projektverwaltung.model.Aufgabe;
import de.thd.projektverwaltung.model.Role;
import de.thd.projektverwaltung.model.Time;
import de.thd.projektverwaltung.model.User;
import de.thd.projektverwaltung.repository.RoleRepository;
import de.thd.projektverwaltung.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
    

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH)+1;
        user.setZeitkonto((cal.getActualMaximum(Calendar.DAY_OF_MONTH)-dayOfMonth)*(160/(cal.getActualMaximum(Calendar.DAY_OF_MONTH))));
        user.setMonth(month);
        Role userRole = roleRepository.findByRole("MEMBER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    @Autowired
    UserService userService;
    @Autowired
    TimeService timeService;
    @Autowired
    AufgabenService aufgabenService;

    public void kapaChange(){
        List<User> list = userService.getAllUsers();
        for(int i = 0; i<list.size(); i++){
            User user = list.get(i);
            Calendar cal = Calendar.getInstance();
            int month = cal.get(Calendar.MONTH)+1;
            if (user.getMonth() != month){
                List<Time> timelist = timeService.getAllTime();
                List<Aufgabe> aufgabenlist = aufgabenService.getAllAufgaben();
                userService.saveKapa(user);
                for (int j = 0; j<timelist.size();j++){
                    for (int k = 0; k<aufgabenlist.size();k++){
                        if(timelist.get(j).getAufgabe() == aufgabenlist.get(k)){
                            aufgabenlist.get(k).setAufwand(aufgabenlist.get(k).getAufwand()-timelist.get(j).getZeit());
                            timelist.get(j).setZeit(0);
                            timeService.saveTime(timelist.get(j));
                            aufgabenService.saveAufgabe(aufgabenlist.get(k));
                        }
                    }
                }
            }
        }
    }
    public User saveKapa(User user){
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH)+1;
        user.setMonth(month);
        user.setZeitkonto(160);
        return userRepository.save(user);
    }

    @Autowired
    private UserRepository repo;

    public List<User> getAllUsers()
    {
        List<User> result = (List<User>) repo.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<User>();
        }
    }



}
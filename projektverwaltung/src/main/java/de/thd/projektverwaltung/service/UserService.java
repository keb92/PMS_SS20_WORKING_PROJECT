package de.thd.projektverwaltung.service;

import de.thd.projektverwaltung.model.Role;
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
        user.setZeitkonto((cal.getActualMaximum(Calendar.DAY_OF_MONTH)-dayOfMonth)*(160/(cal.getActualMaximum(Calendar.DAY_OF_MONTH))));
        Role userRole = roleRepository.findByRole("MEMBER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
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
package com.skrylov.WebApp.controller;

import com.skrylov.WebApp.domain.Role;
import com.skrylov.WebApp.domain.User;
import com.skrylov.WebApp.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @GetMapping("/login")
    public String login(Map<String,Object> model){
        List<User> users = userRepo.findAll();
        Map<String,String> map = new HashMap<>();
        for (User user : users) {
            map.put(user.getUsername(), user.getFirstName() + " " + user.getSecondName());
        }
        Set<Map.Entry<String,String>> entrySet = map.entrySet();
        model.put("entrySet",entrySet);

        return "login";
    }

    @PostMapping("/registration")
    public String addNewUser(User user, Map<String, Object> model){
        User userFromDb = userRepo.findByUsernameIgnoreCase(user.getUsername());

        if(userFromDb != null){
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}

package com.skrylov.WebApp.controller;

import com.skrylov.WebApp.domain.Message;
import com.skrylov.WebApp.domain.Role;
import com.skrylov.WebApp.domain.User;
import com.skrylov.WebApp.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class EditController {
    @Autowired
    UserRepo userRepo;


    @GetMapping("/edit")
    public String editUser(@AuthenticationPrincipal User user, Map<String,Object> model){
        User userFromDb = userRepo.findByUsernameIgnoreCase(user.getUsername());

        if(userFromDb.getFirstName() == null) userFromDb.setFirstName("");
        if(userFromDb.getSecondName() == null) userFromDb.setSecondName("");

        model.put("firstName",userFromDb.getFirstName());
        model.put("secondName",userFromDb.getSecondName());

        return "edit";
    }

    @PostMapping("/edit")
    public String editUserSave(@AuthenticationPrincipal User user,String firstName,String secondName, Map<String, Object> model){
        User userFromDb = userRepo.findByUsernameIgnoreCase(user.getUsername());

        userFromDb.setFirstName(firstName);
        userFromDb.setSecondName(secondName);
        userRepo.save(userFromDb);

        return "redirect:/main";
    }
}

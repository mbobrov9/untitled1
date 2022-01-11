package com.projekt.pluk.controllers;

import com.projekt.pluk.models.laws;
import com.projekt.pluk.models.Role;
import com.projekt.pluk.models.punishent;
import com.projekt.pluk.models.User;
import com.projekt.pluk.repos.UserRepo;
import com.projekt.pluk.repos.punishment_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private punishment_repo punish;
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model,@RequestParam String Type,@RequestParam String Rolle) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setPants("Нет");
        user.setType(Type);
        if (Rolle=="ПЖ")
            user.setRoles(Collections.singleton(Role.PG));
        if (Rolle=="Эцилоп")
            user.setRoles(Collections.singleton(Role.ECILOP));
        if (Rolle=="ШРО")
            user.setRoles(Collections.singleton(Role.SHT));
        if (Rolle=="Фабрика")
            user.setRoles(Collections.singleton(Role.FAB));
        if (Rolle=="Плюканин")
            user.setRoles(Collections.singleton(Role.PLUK));

        userRepo.save(user);

        return "redirect:/login";
    }
    @GetMapping("/new_punishment")
    public String new_punishment() {
        return "new_punishment";
    }

    @PostMapping("/new_punishment")
    public String addpunishment(  @RequestParam String title,  @RequestParam int number) {

        punishent pun = new punishent(title, number);
        punish.save(pun);
        return "redirect:/";
    }
}
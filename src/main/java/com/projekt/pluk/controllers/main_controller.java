package com.projekt.pluk.controllers;


import com.projekt.pluk.models.User;
import com.projekt.pluk.models.laws;
import com.projekt.pluk.repos.UserRepo;
import com.projekt.pluk.repos.laws_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Map;
@Controller
public class main_controller {
    @Autowired
    private UserRepo Users;
    @GetMapping("/")
    public String home(Map<String, Object> mode)  {

        return "/home";
    }
    @GetMapping("/test")
    public String test(Model model)  {
        model.addAttribute("title", "dude");

        return "test";
    }
    @PostMapping("/user/{id}")
    public String edit_law(@PathVariable(value = "id") long id,  Model model)  {
        Optional <User> usr =Users.findById(id);
        ArrayList<User> user = new ArrayList<>();
        usr.ifPresent(user::add);
        model.addAttribute("user", user);
        return "User";
    }
}
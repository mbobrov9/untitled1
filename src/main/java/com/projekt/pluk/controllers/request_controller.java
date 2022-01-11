package com.projekt.pluk.controllers;

import com.projekt.pluk.models.*;
import com.projekt.pluk.repos.UserRepo;
import com.projekt.pluk.repos.laws_repo;
import com.projekt.pluk.repos.punishment_repo;
import com.projekt.pluk.repos.request_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class request_controller {
    @Autowired
    private request_repo Request_repo;
    @Autowired
    private UserRepo Users;
    @Autowired
    private laws_repo law;
    @Autowired
    private punishment_repo punn;
    @GetMapping("/new_request")
    public String new_request(Model model)  {
        Iterable<laws> laws = law.findAll();
        model.addAttribute("laws", laws);
        Iterable<User> usr = Users.findByType("Поцак");
        Iterable<User> usr2 = Users.findByType("Чатланин");
        model.addAttribute("user", usr);
        model.addAttribute("user2", usr2);
        return "new_request";
    }
    @PostMapping("/new_request")
    public String post_new_request(@AuthenticationPrincipal User user,  @RequestParam long id, @RequestParam String fulltext, @RequestParam long type, Model model)  {

        User second =Users.findById(id).orElse(new User());
        laws lawss = law.findById(type).orElse(new laws());

        request request = new request(lawss, fulltext,user, second);
        int score=0;
        punishent pun= lawss.getPunish();
        score+=pun.getScore();
        if(user.getPants()=="Жёлтые")
            score++;
        if(user.getPants()=="Малиновые")
            score=score+2;
        if(second.getPants()=="Жёлтые")
            score--;
        if(second.getPants()=="Малиновые")
            score=score-2;
        if(user.getType()=="Чатланин")
            score=score+1;
        if(second.getPants()=="Чатланин")
            score=score-1;
        if (score>5)
            score=5;
        if (score<1)
            score=1;

        request.setRec(punn.findByScore(score));
        Request_repo.save(request);
        return "redirect:/";

    }
    @PreAuthorize("hasAuthority('ECILOP')")
    @GetMapping("/request")
    public String home(Model model) {

        Iterable<request> request = Request_repo.findAll();
        model.addAttribute("request", request);
        return "request";
    }

    @GetMapping("/request/{id}")
    public String edit_law(@PathVariable(value = "id") long id, Model model)  {
        Optional<request> req =Request_repo.findById(id);
        Iterable<punishent> pun = punn.findAll();
        model.addAttribute("punish", pun);
        ArrayList<request> request = new ArrayList<>();
        req.ifPresent(request::add);
        model.addAttribute("request", request);
        return "full_req";
    }
    @PostMapping("/request/{id}")
    public String edit_law(@PathVariable(value = "id") long id, @RequestParam String type,  Model model)  {
        request editlaw = Request_repo.findById(id).orElse(new request());

        //editlaw.setPunishment(type);

        Request_repo.save(editlaw);
        return "redirect:/request";

    }
}

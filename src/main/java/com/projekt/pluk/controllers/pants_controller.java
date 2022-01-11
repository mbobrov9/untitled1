package com.projekt.pluk.controllers;

import com.projekt.pluk.models.laws;
import com.projekt.pluk.models.pants_req;
import com.projekt.pluk.models.User;
import com.projekt.pluk.models.request;
import com.projekt.pluk.repos.UserRepo;
import com.projekt.pluk.repos.pants_req_repo;
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

@Controller

public class pants_controller {
    @Autowired
    private pants_req_repo Pants_req_repo;
    @Autowired
    private UserRepo user;
    @PreAuthorize("hasAuthority('PLUK')")
    @GetMapping("/new_pants")
    public String new_pants(Model model)  {
        model.addAttribute("title", "Dude");

        return "new_pants";
    }

    @PreAuthorize("hasAuthority('PLUK')")
    @GetMapping("/my_pants")
    public String my_pants(@AuthenticationPrincipal User user,Model model)  {
        long us = user.getId();
        Iterable<pants_req> pants_req = Pants_req_repo.findByAuthor(user);
        model.addAttribute("pants_req", pants_req);
        long id =user.getId();
        model.addAttribute("id", id);
        return "my_pants";
    }

    @PreAuthorize("hasAuthority('SHT')")
    @GetMapping("/pants_for_pg")
    public String pants_por_pg(Model model) {
        Iterable<pants_req> pants_req = Pants_req_repo.findByDecision("новое");
        Iterable<pants_req> pants_req2 = Pants_req_repo.findByDecision("Одобрено");
        Iterable<pants_req> pants_req3 = Pants_req_repo.findByDecision("Пошит");
        model.addAttribute("pants_req", pants_req);
        model.addAttribute("pants_req2", pants_req2);
        model.addAttribute("pants_req3", pants_req3);
        return "/pants_for_pg";
    }

    @PreAuthorize("hasAuthority('FAB')")
    @GetMapping("/pants_for_fab")
    public String pants_por_fab(Model model) {
        Iterable<pants_req> pants_req = Pants_req_repo.findByDecision("На производстве");
        model.addAttribute("pants_req", pants_req);
        return "/pants_for_fab";
    }


    @PostMapping("/new_pants")
    public String post_new_pants(@AuthenticationPrincipal User user, @RequestParam String number, @RequestParam String fulltext, @RequestParam String type, Model model)  {
        //for (int i = 0; i < 10000; i++) {
            pants_req pants_req = new pants_req(number, type, fulltext, user);
            Pants_req_repo.save(pants_req);
      //  }
        return "/new_pants";

    }

    @PreAuthorize("hasAuthority('PG')")
    @GetMapping("/pants")
    public String pants(Model model) {

        Iterable<pants_req> pants_req = Pants_req_repo.findByDecision("На одобрении");
        model.addAttribute("pants_req", pants_req);
        return "/pants";
    }
    @PostMapping("/pants/{id}/delete")
    public String delete_law(@PathVariable(value = "id") long id, Model model)  {
        pants_req del_pan = Pants_req_repo.findById(id).orElse(new pants_req());

        Pants_req_repo.delete(del_pan);
        return "redirect:/pants";

    }
    @PostMapping("/pants/{id}")
    public String edit_pants(@PathVariable(value = "id") long id,  Model model)  {
        pants_req editlaw = Pants_req_repo.findById(id).orElse(new pants_req());

        editlaw.setDecision("Одобрено");

        Pants_req_repo.save(editlaw);
        return "redirect:/pants";

    }
    @PostMapping("/pants_for_pg/{id}")
    public String edit_pants_for_pg(@PathVariable(value = "id") long id,  Model model)  {
        pants_req editpan = Pants_req_repo.findById(id).orElse(new pants_req());

        editpan.setDecision("На одобрении");

        Pants_req_repo.save(editpan);
        return "redirect:/pants_for_pg";

    }
    @PostMapping("/pants_for_pgg/{id}")
    public String edit_pants_for_pgg(@PathVariable(value = "id") long id,  Model model)  {
        pants_req editpan = Pants_req_repo.findById(id).orElse(new pants_req());

        editpan.setDecision("На производстве");

        Pants_req_repo.save(editpan);
        return "redirect:/pants_for_pg";

    }
    @PostMapping("/pants_for_pggg/{id}")
    public String edit_pants_for_pggg(@PathVariable(value = "id") long id,  Model model)  {
        pants_req editpan = Pants_req_repo.findById(id).orElse(new pants_req());

        editpan.setDecision("Готовы к выдаче");

        Pants_req_repo.save(editpan);
        return "redirect:/pants_for_pg";

    }
    @PostMapping("/pants_for_fab/{id}")
    public String edit_pants_for_fab(@PathVariable(value = "id") long id,  Model model)  {
        pants_req editpan = Pants_req_repo.findById(id).orElse(new pants_req());
      //  User us = editpan.getAuthor();
      //  us.setPants(editpan.getType());
        editpan.setDecision("Пошит");

        Pants_req_repo.save(editpan);
        return "redirect:/pants_for_fab";

    }




}

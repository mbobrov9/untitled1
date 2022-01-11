package com.projekt.pluk.controllers;

import com.projekt.pluk.models.laws;
import com.projekt.pluk.models.pants_req;
import com.projekt.pluk.models.punishent;
import com.projekt.pluk.repos.laws_repo;
import com.projekt.pluk.repos.punishment_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class laws_controller {
    @Autowired
    private punishment_repo punish;

        @Autowired
        private laws_repo Laws;
        @GetMapping("/laws")
        public String law(Model model)  {
            model.addAttribute("title", "Dude");
            Iterable<laws> laws=Laws.findAll();
            model.addAttribute("laws", laws);

            return "laws";
        }
        @PreAuthorize("hasAuthority('PG')")
        @GetMapping("/laws/{id}")
        public String lawEdit(@PathVariable(value = "id") long id, Model model)  {
            Optional <laws> laws =Laws.findById(id);
            Iterable<punishent> pun = punish.findAll();
            ArrayList<laws> law = new ArrayList<>();
            laws.ifPresent(law::add);
            model.addAttribute("laws", law);
            model.addAttribute("punish", pun);
            return "law_edit";

        }
        @PreAuthorize("hasAuthority('PG')")
        @GetMapping("/new_law")
        public String new_law(Model model) {
            model.addAttribute("title", "Dude");
            Iterable<punishent> pun = punish.findAll();
            model.addAttribute("punish", pun);
            return "new_law";
        }
        @PostMapping("/new_law")
            public String post_new_law(@RequestParam String name, @RequestParam String anons, @RequestParam Long type, String fulltext, Model model)  {
            punishent pun= punish.findById(type).orElse(new punishent());
            laws newlaw = new laws(name, anons, pun, fulltext);
            Laws.save(newlaw);
            return "/laws";

        }

        @PostMapping("/laws/{id}")
        public String edit_law(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam String anons, @RequestParam Long punishment, String fulltext, Model model)  {
            punishent pun= punish.findById(punishment).orElse(new punishent());
            laws editlaw = Laws.findById(id).orElse(new laws());

            editlaw.setTitle(name);
            editlaw.setAnons(anons);
            editlaw.setPunish(pun);
            editlaw.setFull_text(fulltext);
            Laws.save(editlaw);
            return "/laws";

        }

        @PostMapping("/laws/{id}/delete")
        public String delete_law(@PathVariable(value = "id") long id, Model model)  {
            laws editlaw = Laws.findById(id).orElse(new laws());

            Laws.delete(editlaw);
            return "redirect:/laws";

        }


}

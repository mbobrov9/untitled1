package com.projekt.pluk.models;

import javax.persistence.*;

@Entity
public class laws {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title, anons,punishment, full_text;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "punishent_id")
    private punishent punish;

    public punishent getPunish() {
        return punish;
    }

    public void setPunish(punishent punish) {
        this.punish = punish;
    }

    public String getPunishment() {
        return punishment;
    }

    public void setPunishment(String punishment) {
        this.punishment = punishment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnons() {
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }
    public laws() {
    }

    public laws(String name, String anons, punishent punish, String full_text) {
        this.title = name;
        this.anons = anons;
        this.punish = punish;
        this.full_text = full_text;
    }
}

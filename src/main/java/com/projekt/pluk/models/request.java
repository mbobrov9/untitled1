package com.projekt.pluk.models;

import javax.persistence.*;

@Entity
public class request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;




    private String secondary, type, full_text, punishment,recommend;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sec_id")
    private User second;

    public punishent getRec() {
        return rec;
    }

    public void setRec(punishent rec) {
        this.rec = rec;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "punishment_id")
    private punishent punish;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rec_id")
    private punishent rec;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "law_id")
    private laws law;

    public laws getLaw() {
        return law;
    }

    public void setLaw(laws law) {
        this.law = law;
    }

    public punishent getPunish() {
        return punish;
    }

    public void setPunish(punishent punish) {
        this.punish = punish;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public User getSecond() {
        return second;
    }

    public void setSecond(User second) {
        this.second = second;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getPunishment() {
        return punishment;
    }

    public void setPunishment(String punishment) {
        this.punishment = punishment;
    }

    public String getSecondary() {
        return secondary;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }

    public request() {
    }

    public request( laws law, String full_text,User user,User second) {

        this.law = law;
        this.full_text = full_text;
        this.author=user;
        this.second=second;
    }
}

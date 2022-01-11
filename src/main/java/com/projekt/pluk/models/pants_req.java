package com.projekt.pluk.models;

import javax.persistence.*;

@Entity
public class pants_req {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    private String count, type, full_text, decision;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
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

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
    public pants_req() {
    }
    public pants_req(String number, String type, String full_text, User user) {
        this.count = number;
        this.type = type;
        this.full_text = full_text;
        this.decision = "новое";
        this.author=user;
    }
}

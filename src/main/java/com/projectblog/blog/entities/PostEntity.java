package com.projectblog.blog.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PostEntity {
    private String title;
    private String content;
    private String picture;
    private String date;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public PostEntity() {
    }

    public PostEntity(String title, String content, String picture, String date) {
        this.setId(id);
        this.setTitle(title);
        this.setContent(content);
        this.setPicture(picture);
        this.setDate(date);
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

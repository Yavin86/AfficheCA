package com.yavin.afficheca.domain;

import java.util.List;

/**
 * Class that represents a Event in the domain layer.
 */
public class Event {

    private final String id;

    public Event(String id) {
        this.id = id;
    }

    private String title;
    private String detail;
    private String description;
    private List<String> imageLinks;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(List<String> imageLinks) {
        this.imageLinks = imageLinks;
    }
}

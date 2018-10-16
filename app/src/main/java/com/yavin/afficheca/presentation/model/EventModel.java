package com.yavin.afficheca.presentation.model;

import java.util.List;

/**
 * Class that represents a event in the presentation layer.
 */
public class EventModel {
    private final String id;

    public EventModel(String id) {
        this.id = id;
    }

    private String title;
    private String detail;
    private String description;
    private List<String> imageLinks;

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return (detail == null || detail.isEmpty()) ? "" : detail;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return (description == null || description.isEmpty()) ? "" : description;
    }

    public List<String> getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(List<String> imageLinks) {
        this.imageLinks = imageLinks;
    }
}

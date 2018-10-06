package com.yavin.afficheca.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * User Entity used in the data layer.
 */
public class EventEntity {

    public EventEntity(String id, String title, String detail, String description, List<String> imageLinks) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.description = description;
        this.imageLinks = imageLinks;
    }

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("detail")
    private String detail;

    @SerializedName("description")
    private String description;

    @SerializedName("imageLinks")
    private List<String> imageLinks;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDetail() {
        return detail;
    }

    public List<String> getImageLinks() {
        return imageLinks;
    }
}

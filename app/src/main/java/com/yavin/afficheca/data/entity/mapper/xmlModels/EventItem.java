package com.yavin.afficheca.data.entity.mapper.xmlModels;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

public class EventItem {

    @Element(name = "id", required = false)
    private String id;

    @ElementList(name = "tags", required = false)
    private List<String> tags;

    @Element(name = "title", required = false)
    private String title;

    @Element(name = "detail", required = false)
    private String detail;

    @Element(name = "tag", required = false)
    private String tag;

    @Element(name = "description", required = false)
    private String description;

    @ElementList(name = "images", required = false)
    private List<String> images;

    @Element(name = "date", required = false)
    private String date;

    @Element(name = "dateActual", required = false)
    private String dateActual;

    @Element(name = "banner", required = false)
    private String banner;

    public String getId() {
        return id;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getTag() {
        return tag;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getImages() {
        return images;
    }

    public String getDate() {
        return date;
    }

    public String getDateActual() {
        return dateActual;
    }

    public String getBanner() {
        return banner;
    }

    @Override
    public String toString()    {
        return "[id: " + id + ", title: " + title + "detail: " + detail + "]";
    }
}

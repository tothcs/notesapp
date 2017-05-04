package com.github.tothcs.model;

import com.orm.SugarRecord;

public class Note extends SugarRecord {
    private Long id = null;
    private String title;
    private String description;
    private Category category;
    private Priority priority;

    public Note() {

    }

    public Note(Long id, String title, String description, Category category, Priority priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
    }

    public Note(String title, String description, Category category, Priority priority) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}

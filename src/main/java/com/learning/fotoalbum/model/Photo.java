package com.learning.fotoalbum.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name= "photos")
public class Photo {

    //ATTRIBUTES / COLUMNS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "The title can not be empty :(")
    private String title;

    @NotEmpty(message = "Text can not be empty :( ")
    @Size(min = 5 ,max = 250, message = "The text must be greater than 5 and smaller than 250 characters :(" )
    @Lob
    private String description;

    @NotEmpty(message = "Need to upload a photo :(")
    @Column(nullable = false)
    private String url;

    @NotNull(message = "Need to make a choice :(")
    private boolean visible;

    //RELATIONS
    @ManyToMany
    @JoinTable(
            name = "photos_categories",
            joinColumns = @JoinColumn(name = "photo_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    //GETTER & SETTER

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}

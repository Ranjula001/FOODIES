package com.project.foodies.PostManagement;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity 
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "userId")
    private Integer userId; 

    private String caption;

    private String expression;

    @ElementCollection
    @Lob
    @Column(name = "photo", nullable = false, columnDefinition = "mediumblob")
    private List<byte[]> photos = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;

    }

    

    public List<byte[]> getPhotos() {
        return photos;

    }

    public void setPhotos(List<byte[]> photos) {
        this.photos = photos;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

   
    
}


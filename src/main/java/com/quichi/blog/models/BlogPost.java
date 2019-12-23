package com.quichi.blog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
//@Builder
//@NoArgsConstructor
//@RequiredArgsConstructor
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private Date createdDate;

    public int getId() {
        return id;
    }

    public BlogPost() {

    }
    public BlogPost(String title, String description, Date createdDate) {
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
    }

    public BlogPost(int id, String title, String description, Date createdDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
    }

    public String getTitle() {
        return title;
    }
    public Date getCreatedDate() {
        return createdDate;
    }

    public String getDescription() {
        return description;
    }
}

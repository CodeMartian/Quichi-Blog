package com.quichi.blog.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
//@Builder
//@NoArgsConstructor
//@RequiredArgsConstructor
public class BlogPost {
    @Id
    private int id;
    private String title;
    private String description;
    private Date createdDate;

    public BlogPost() {
    }
    public BlogPost(String title, String description, Date createdDate) {
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

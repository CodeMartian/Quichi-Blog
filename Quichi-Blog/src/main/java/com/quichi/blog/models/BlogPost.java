package com.quichi.blog.models;

import lombok.*;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;


    @Transient
    private List<Comment> comments;
    private Date createdDate;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getDescription() {
        return description;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

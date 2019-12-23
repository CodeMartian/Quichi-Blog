package com.quichi.blog.models;

import lombok.*;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String title;
    @NonNull
    private String description;
    private Date createdDate;

    public int getId() {
        return id;
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

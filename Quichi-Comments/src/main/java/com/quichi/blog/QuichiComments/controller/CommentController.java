package com.quichi.blog.QuichiComments.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comments")
public class CommentController {

    /*
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Animal create(@RequestBody @Valid final Animal animal) {
        return animalService.create(animal);
    }
     */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create() {

    }

}

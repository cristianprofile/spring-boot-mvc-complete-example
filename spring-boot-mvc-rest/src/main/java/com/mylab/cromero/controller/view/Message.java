package com.mylab.cromero.controller.view;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by cristianromeromatesanz on 30/08/16.
 */
public class Message {

    @JsonView(View.Summary.class)
    private Long id;

    @JsonView(View.Summary.class)
    private String name;

    @JsonView(View.Internal.class)
    private String title;



    private String body;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}

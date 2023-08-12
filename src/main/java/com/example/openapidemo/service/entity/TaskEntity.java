package com.example.openapidemo.service.entity;

import lombok.Value;

@Value
public class TaskEntity {
    public TaskEntity(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    private Long id;
    private String title;

    public Long getId(){
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }
}

package com.example.openapidemo.repository.record;

import lombok.Value;

@Value
public class TaskRecord {
    public TaskRecord(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    private Long id;
    private String title;

    public Long getId() {
        return this.id;
    }

    public String getTitle() { return this.title; }
}

package com.example.openapidemo.service.entity;

public class TaskEntityNotFoundException extends RuntimeException {
    private Long taskId;
    public TaskEntityNotFoundException(Long taskId) {
        super("TaskEntity (id = " + taskId + ") is not found.");
        this.taskId = taskId;
    }
}

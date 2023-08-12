package com.example.openapidemo.service;


import com.example.openapidemo.repository.TaskRepository;
import com.example.openapidemo.repository.record.TaskRecord;
import com.example.openapidemo.service.entity.TaskEntity;
import com.example.openapidemo.service.entity.TaskEntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    public TaskEntity find(Long taskId){
        return taskRepository.select(taskId)
                .map(record -> new TaskEntity(record.getId(), record.getTitle()))
                .orElseThrow(() -> new TaskEntityNotFoundException(taskId));
    }
}

package com.example.openapidemo.service;


import com.example.openapidemo.repository.TaskRepository;
import com.example.openapidemo.repository.record.TaskRecord;
import com.example.openapidemo.service.entity.TaskEntity;
import com.example.openapidemo.service.entity.TaskEntityNotFoundException;
import com.example.todoapi.model.TaskForm;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    private final String DataIntegrityViolationExceptionMsg = "DBの整合性制約に違反しました。クエリパラメーターの値に「-」がついていないか確認して下さい。";

    public TaskEntity find(Long taskId){
        return taskRepository.select(taskId)
                .map(record -> new TaskEntity(record.getId(), record.getTitle()))
                .orElseThrow(() -> new TaskEntityNotFoundException(taskId));
    }

    public TaskEntity create(String title) {
        var record = new TaskRecord(null, title);
        taskRepository.insert(record);
        return new TaskEntity(record.getId(), record.getTitle());
    }

    public List<TaskEntity> list(Integer limit, Long offset) {
        if(limit.toString().charAt(0) == '-' || offset.toString().charAt(0) == '-'){
            throw new DataIntegrityViolationException(DataIntegrityViolationExceptionMsg);
        }
        return taskRepository.list(limit, offset).stream()
                .map(record -> new TaskEntity(record.getId(), record.getTitle()))
                .toList();
    }

    public TaskEntity update(Long taskId, String title) {
        taskRepository.select(taskId)
                .orElseThrow(() -> new TaskEntityNotFoundException(taskId));
        var record = new TaskRecord(taskId, title);
        taskRepository.update(record);
        return find(taskId);
    }

    public void delete(Long taskId) {
        taskRepository.select(taskId)
                .orElseThrow(() -> new TaskEntityNotFoundException(taskId));
        taskRepository.delete(taskId);
    }
}

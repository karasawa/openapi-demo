package com.example.openapidemo.repository;

import com.example.openapidemo.repository.record.TaskRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface TaskRepository {
    @Select("SELECT * FROM tasks WHERE id = #{taskId};")
    Optional<TaskRecord> select(Long taskId);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO tasks (title) VALUES (#{title});")
    void insert(TaskRecord record);
}

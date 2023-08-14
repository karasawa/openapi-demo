package com.example.openapidemo.repository;

import com.example.openapidemo.repository.record.TaskRecord;
import io.micrometer.observation.ObservationFilter;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TaskRepository {
    @Select("SELECT id, title FROM tasks WHERE id = #{taskId};")
    Optional<TaskRecord> select(Long taskId);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO tasks (title) VALUES (#{title});")
    void insert(TaskRecord record);

    @Select("SELECT id, title FROM tasks LIMIT #{limit} OFFSET #{offset};")
    List<TaskRecord> list(Integer limit, Long offset);

    @Update("UPDATE tasks SET title = #{title} WHERE id = #{id};")
    void update(TaskRecord record);

    @Delete("DELETE FROM tasks WHERE id = #{taskId};")
    void delete(Long taskId);
}

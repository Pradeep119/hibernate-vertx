package com.example.data;

import com.example.dto.TaskDto;
import com.example.dto.TaskList;
import io.vertx.core.Future;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
  Future<TaskDto> createTask (TaskDto taskDto);
  Future<TaskDto> updateTask (TaskDto taskDto);
  Future<Void> removeTask (Integer id);
  Future<Optional<TaskDto>> findTaskById (Integer id);
  Future<TaskList> findTasksByUser (Integer userId);
}

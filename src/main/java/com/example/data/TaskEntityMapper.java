package com.example.data;

import com.example.dto.TaskDto;
import com.example.model.Task;

import java.util.function.Function;

class TaskEntityMapper implements Function<TaskDto, Task> {
  @Override
  public Task apply(TaskDto taskDto) {
    Task task = new Task();
    task.setId(taskDto.id());
    task.setUserId(taskDto.userId());
    task.setContent(taskDto.content());
    task.setCompleted(taskDto.completed());
    task.setCreatedAt(taskDto.createdAt());
    return task;
  }
}

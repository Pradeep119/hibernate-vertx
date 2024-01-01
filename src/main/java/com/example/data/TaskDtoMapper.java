package com.example.data;

import com.example.dto.TaskDto;
import com.example.model.Task;

import java.util.function.Function;

class TaskDtoMapper implements Function<Task, TaskDto> {
  @Override
  public TaskDto apply(Task task) {
    return new TaskDto(task.getId(), task.getUserId(), task.getContent(), task.isCompleted(), task.getCreatedAt());
  }
}

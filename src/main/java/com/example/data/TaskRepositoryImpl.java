package com.example.data;

import com.example.dto.TaskDto;
import com.example.dto.TaskList;
import com.example.model.Task;
import io.vertx.core.Future;
import jakarta.persistence.criteria.*;
import org.hibernate.reactive.stage.Stage;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

public record TaskRepositoryImpl (Stage.SessionFactory sessionFactory) implements TaskRepository{

  @Override
  public Future<TaskDto> createTask(TaskDto taskDto) {
//    TaskEntityMapper entityMapper = new TaskEntityMapper();
//    Task entity = entityMapper.apply(taskDto);
//    CompletionStage<Void> result = sessionFactory.withTransaction((s,t) -> s.persist(entity));
//    TaskDtoMapper dtoMapper = new TaskDtoMapper();
//    Future<TaskDto> future = Future.fromCompletionStage(result).map(v -> dtoMapper.apply(entity));
//    return future;





    Function<TaskDto, Task> myfun = (taskDto1) -> {
      Task task = new Task();
      task.setId(taskDto1.id());
      task.setUserId(taskDto1.userId());
      task.setContent(taskDto1.content());
      task.setCompleted(taskDto1.completed());
      task.setCreatedAt(taskDto1.createdAt());
      return task;
    };


    Task entity = myfun.apply(taskDto);
    CompletionStage<Void> result = sessionFactory.withTransaction((s,t) -> s.persist(entity));
    TaskDtoMapper dtoMapper = new TaskDtoMapper();
    Future<TaskDto> future = Future.fromCompletionStage(result).map(v -> dtoMapper.apply(entity));
    return future;
  }

  @Override
  public Future<TaskDto> updateTask(TaskDto taskDto) {
    CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
    CriteriaUpdate<Task> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Task.class);
    Root<Task> root =criteriaUpdate.from(Task.class);
    Predicate predicate = criteriaBuilder.equal(root.get("id"), taskDto.id());

    criteriaUpdate.set("content", taskDto.content());
    criteriaUpdate.set("completed", taskDto.completed());

    criteriaUpdate.where(predicate);

    CompletionStage<Integer>  result = sessionFactory.withTransaction((s,t) -> s.createQuery(criteriaUpdate).executeUpdate());
//    Future<Void> future = Future.fromCompletionStage(result).map(r-> taskDto);
//    return future;
    return null;
  }


  @Override
  public Future<Void> removeTask(Integer id) {
    CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
    CriteriaDelete<Task> criteriaDelete = criteriaBuilder.createCriteriaDelete(Task.class);
    Root<Task> root = criteriaDelete.from(Task.class);
    Predicate predicate = criteriaBuilder.equal(root.get("id"), id);
    criteriaDelete.where(predicate);

    //DELETE FROM task WHERE id = [id]

    CompletionStage<Integer>  result = sessionFactory.withTransaction((s,t) -> s.createQuery(criteriaDelete).executeUpdate());
    Future<Void> future = Future.fromCompletionStage(result).compose(r -> Future.succeededFuture());
    return future;
  }

  @Override
  public Future<Optional<TaskDto>> findTaskById(Integer id) {
    TaskDtoMapper taskDtoMapper = new TaskDtoMapper();
    CompletionStage<Task> result = sessionFactory.withTransaction((s,t) -> s.find(Task.class, id));
    Future<Optional<TaskDto>> future = Future.fromCompletionStage(result)
      .map(r -> Optional.ofNullable(r))
      .map(r -> r.map(taskDtoMapper));
    return future;
  }

  @Override
  public Future<TaskList> findTasksByUser(Integer userId) {
    return null;
  }
}

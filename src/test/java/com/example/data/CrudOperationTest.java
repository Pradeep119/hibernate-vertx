package com.example.data;

import com.example.dto.ProjectDto;
import com.example.dto.TaskDto;
import com.example.model.Task;
import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.hibernate.cfg.Configuration;
import org.hibernate.reactive.provider.ReactiveServiceRegistryBuilder;
import org.hibernate.reactive.stage.Stage;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Properties;

@ExtendWith(VertxExtension.class)
class CrudOperationTest {

  TaskRepositoryImpl taskRepository;
  ProjectRepositoryImpl projectRepository;

  @BeforeEach
  void setup(Vertx vertx, VertxTestContext context){
    Properties hibernateProps = new Properties();
    hibernateProps.put("hibernate.connection.url","jdbc:postgresql://localhost:5432/api_backend");
    hibernateProps.put("hibernate.connection.username", "postgres");
    hibernateProps.put("hibernate.connection.password", "postgres");
//    hibernateProps.put("javax.persistence.schema-generation.database.action", "create");

    Configuration hibernateConfiguration = new Configuration();
    hibernateConfiguration.setProperties(hibernateProps);
    hibernateConfiguration.addAnnotatedClass(Task.class);

    ServiceRegistry serviceRegistry = new ReactiveServiceRegistryBuilder()
      .applySettings(hibernateConfiguration.getProperties()).build();

    Stage.SessionFactory sessionFactory = hibernateConfiguration.buildSessionFactory(serviceRegistry).unwrap(Stage.SessionFactory.class);
    taskRepository = new TaskRepositoryImpl(sessionFactory);
    context.completeNow();
  }

  @Test
  void createTaskTest(Vertx vertx, VertxTestContext context){
    TaskDto taskDto = new TaskDto(null, 1, "my task", false, LocalDateTime.now());
    context.verify(()->{
      taskRepository.createTask(taskDto)
        .onFailure(err -> context.failNow(err))
        .onSuccess(result -> {
          Assertions.assertNotNull(result);
          Assertions.assertNotNull(result.id());
          context.completeNow();
        });
    });
  }

  @Test
  void findTaskByIdDoesNotExistTest(Vertx vertx, VertxTestContext context){
    context.verify(() ->{
      taskRepository.findTaskById(1)
        .onSuccess(r -> {
          Assertions.assertTrue(r.isEmpty());
          context.completeNow();
        })
        .onFailure(err -> context.failNow(err));
    });
  }

  @Test
  void findTaskByIdExistTest(Vertx vertx, VertxTestContext context){
    context.verify(() ->{
      taskRepository.findTaskById(201)
        .onSuccess(r -> {
          TaskDto taskDto = r.get();
          System.out.println(taskDto.id());
          Assertions.assertEquals(201, taskDto.id());
          context.completeNow();
        })
        .onFailure(err -> context.failNow(err));
    });
  }

  @Test
  void createRelationshipTest(Vertx vertx, VertxTestContext context){
    ProjectDto projectDto = new ProjectDto(null, 1, "My Project");
    context.verify(()->{
        projectRepository.createProject(projectDto)
          .compose(project -> {
            Assertions.assertEquals(1, project.id());
            TaskDto taskDto = new TaskDto(null, 1, "My Task", false, LocalDateTime.now());
            return taskRepository.createTask(taskDto);
          }).onSuccess(result -> {
//            Assertions.assertTrue(result.projectDto().isPresent());
            context.completeNow();
          }).onFailure(err -> context.failNow(err));
    });
  }

}

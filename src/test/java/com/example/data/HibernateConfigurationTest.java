package com.example.data;

import com.example.model.Task;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.hibernate.reactive.provider.ReactiveServiceRegistryBuilder;
import org.hibernate.reactive.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.hibernate.cfg.*;
import org.hibernate.service.*;

import java.time.LocalDateTime;
import java.util.Properties;
import java.util.concurrent.CompletionStage;


@ExtendWith(VertxExtension.class)
public class HibernateConfigurationTest {

  @Test
  void initializeHibernateWithCode(Vertx vertx, VertxTestContext context){
    //1. create properties with config data
    Properties hibernateProps = new Properties();

    //postgres propertis
    hibernateProps.put("hibernate.connection.url","jdbc:postgresql://localhost:5432/api_backend");
    hibernateProps.put("hibernate.connection.username", "postgres");
    hibernateProps.put("hibernate.connection.password", "postgres");
    hibernateProps.put("javax.persistence.schema-generation.database.action", "create");


    //2. create hibernate configuration
    Configuration hibernateConfiguration = new Configuration();
    hibernateConfiguration.setProperties(hibernateProps);
    hibernateConfiguration.addAnnotatedClass(Task.class);


    //3. create service registery
    ServiceRegistry serviceRegistry = new ReactiveServiceRegistryBuilder()
      .applySettings(hibernateConfiguration.getProperties()).build();

    //4. create session factory
    Stage.SessionFactory sessionFactory = hibernateConfiguration.buildSessionFactory(serviceRegistry).unwrap(Stage.SessionFactory.class);

    //5. do something with db
    Task task = new Task();
    task.setContent("Hello this is new task");
    task.setCompleted(false);
    task.setUserId(1);
    task.setCreatedAt(LocalDateTime.now());

    System.out.println("Task ID before insertion is "+ task.getId());

    CompletionStage<Void> insertionResult = sessionFactory.withTransaction((s,t) -> s.persist(task));

    Future<Void> future = Future.fromCompletionStage(insertionResult);
    context.verify(() -> future
      .onFailure(err -> context.failNow(err))
      .onSuccess(r -> {
        System.out.println("Task id after insersion is: "+task.getId());
        context.completeNow();
      })


    );
  }

}

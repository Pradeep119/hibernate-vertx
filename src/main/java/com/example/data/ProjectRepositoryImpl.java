package com.example.data;

import com.example.dto.ProjectDto;
import com.example.dto.ProjectList;
import io.vertx.core.Future;
import org.hibernate.reactive.stage.Stage;

import java.util.Optional;

public record ProjectRepositoryImpl (Stage.SessionFactory sessionFactory) implements ProjectRepository{
  @Override
  public Future<ProjectDto> createProject(ProjectDto projectDto) {
    return null;
  }

  @Override
  public Future<ProjectDto> updateProject(ProjectDto projectDto) {
    return null;
  }

  @Override
  public Future<Void> removeProject(Integer id) {
    return null;
  }

  @Override
  public Future<Optional<ProjectDto>> findProjectById(Integer id) {
    return null;
  }

  @Override
  public Future<ProjectList> findProjectsByUser(Integer userId) {
    return null;
  }
}

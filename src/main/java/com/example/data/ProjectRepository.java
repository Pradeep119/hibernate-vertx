package com.example.data;

import com.example.dto.ProjectDto;
import com.example.dto.ProjectList;
import io.vertx.core.Future;

import java.util.Optional;

public interface ProjectRepository {
  Future<ProjectDto> createProject (ProjectDto projectDto);
  Future<ProjectDto> updateProject (ProjectDto projectDto);
  Future<Void> removeProject (Integer id);
  Future<Optional<ProjectDto>> findProjectById (Integer id);
  Future<ProjectList> findProjectsByUser (Integer userId);
}

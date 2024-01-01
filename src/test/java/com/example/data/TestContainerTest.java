package com.example.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
class TestContainerTest {

  @Container
  PostgreSQLContainer container = new PostgreSQLContainer("postgres:13-alpine")
    .withDatabaseName("testcontainersdb")
    .withUsername("postgres")
    .withPassword("postgres");

  @Test
  void testContainerIsRunningTest(){
    Assertions.assertTrue(container.isCreated());
    Assertions.assertTrue(container.isRunning());
  }


}

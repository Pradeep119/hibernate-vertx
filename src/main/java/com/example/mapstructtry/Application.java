package com.example.mapstructtry;

import com.example.mapstructtry.dto.FilterDto;
import com.example.mapstructtry.entity.FilterEntity;
import com.example.mapstructtry.entity.LifeCycleStateEntity;
import com.example.mapstructtry.mapper.FilterMapper;
import com.example.mapstructtry.mapper.FilterMapperImpl;

import java.util.UUID;

public class Application {
  public static void main(String[] args) {
    FilterEntity filterEntity = new FilterEntity(UUID.randomUUID().toString(), "filtername1", "black", "postman");
    LifeCycleStateEntity lifeCycleStateEntity = new LifeCycleStateEntity("Filter", "published");

    FilterMapper filterMapper = new FilterMapperImpl();
    FilterDto filterDto = filterMapper.filterEntityToDto(filterEntity,lifeCycleStateEntity );
    System.out.println(filterDto.getId());
    System.out.println(filterDto.getState());
  }
}

package com.example.mapstructtry.mapper;

import com.example.mapstructtry.dto.FilterDto;
import com.example.mapstructtry.entity.FilterEntity;
import com.example.mapstructtry.entity.LifeCycleStateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface FilterMapper {

  @Mapping(target = "id" , source = "filterEntity.id")
  @Mapping(target = "color" , source = "filterEntity.color")
  @Mapping(target = "entry" , source = "filterEntity.entry")
  @Mapping(target = "state" , source = "lifeCycleStateEntity.state")
  FilterDto filterEntityToDto(FilterEntity filterEntity, LifeCycleStateEntity lifeCycleStateEntity);

}

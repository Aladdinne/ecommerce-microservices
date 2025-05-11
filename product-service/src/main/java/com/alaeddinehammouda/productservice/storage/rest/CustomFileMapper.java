package com.alaeddinehammouda.productservice.storage.rest;

import com.alaeddinehammouda.productservice.storage.CustomFile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
//to add @Component in the runtime , deja configurer fl pom
public interface CustomFileMapper extends EntityMapper<CustomFileDto, CustomFile> {
    CustomFileMapper INSTANCE = Mappers.getMapper(CustomFileMapper.class);
}

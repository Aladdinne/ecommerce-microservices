package com.alaeddinehammouda.productservice.storage.rest;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import tn.yellowit.jobgate.domain.dto.OfferDto;
import tn.yellowit.jobgate.domain.entities.BaseEntity;
import tn.yellowit.jobgate.domain.entities.Offer;

import java.util.List;

public interface EntityMapper<D, E extends BaseEntity> {
    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    E partialUpdate(D dto, @MappingTarget E entity);

}

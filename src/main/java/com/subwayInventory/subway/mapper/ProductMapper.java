package com.subwayInventory.subway.mapper;

import com.subwayInventory.subway.model.ProductDto;
import com.subwayInventory.subway.repository.model.ProductEntity;
import org.modelmapper.ModelMapper;

public class ProductMapper {
    private static final ModelMapper mapper = new ModelMapper();

    private ProductMapper(){

    }
    public static ProductDto toDto(ProductEntity productEntity){
        return mapper.map(productEntity, ProductDto.class);
    }

    //To Entity
    public static ProductEntity toEntity(ProductDto productDto){
        return mapper.map(productDto, ProductEntity.class);
    }
}

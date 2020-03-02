package org.acacho.orders.adapter.out.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.acacho.orders.adapter.out.dto.MongoProductDto;
import org.acacho.orders.domain.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MongoProductDtoMapper {

  @Autowired
  private ModelMapper modelMapper;

  public Product fromMongoProductDtoToProduct(MongoProductDto mongoProductDto) {
    return modelMapper.map(mongoProductDto, Product.class);
  }

  public List<Product> fromMongoProductDtosToProducts(List<MongoProductDto> mongoProductDtos) {
    return mongoProductDtos.stream()
        .map(this::fromMongoProductDtoToProduct)
        .collect(Collectors.toList());
  }

  public MongoProductDto fromProductToMongoProductDto(Product product) {
    return modelMapper.map(product, MongoProductDto.class);
  }
}

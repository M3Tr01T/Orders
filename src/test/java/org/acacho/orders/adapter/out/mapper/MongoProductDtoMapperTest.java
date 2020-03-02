package org.acacho.orders.adapter.out.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;
import org.acacho.orders.TestOjectBuilder;
import org.acacho.orders.adapter.out.dto.MongoProductDto;
import org.acacho.orders.boot.OrderApp;
import org.acacho.orders.domain.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@SpringBootTest(classes = {OrderApp.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class MongoProductDtoMapperTest {

  @Autowired
  private ModelMapper modelMapper;
  @Autowired
  MongoProductDtoMapper mongoProductDtoMapper;

  @Test
  void fromMongoProductDtoToProduct() {
    MongoProductDto mongoProductDto = TestOjectBuilder.createMongoProductDto();
    var product = mongoProductDtoMapper.fromMongoProductDtoToProduct(mongoProductDto);
    assertEquals(mongoProductDto.getPrice(), product.getPrice());
    assertEquals(mongoProductDto.getCurrency(), product.getCurrency());
    assertEquals(mongoProductDto.getName(), product.getName());
    assertEquals(mongoProductDto.getId(), product.getId());
  }

  @Test
  void fromMongoProductDtosToProducts() {
    List<MongoProductDto> mongoProductDtos = Collections
        .singletonList(TestOjectBuilder.createMongoProductDto());
    var products = mongoProductDtoMapper.fromMongoProductDtosToProducts(mongoProductDtos);
    assertEquals(mongoProductDtos.size(), products.size());
    assertEquals(mongoProductDtos.get(0).getCurrency(), products.get(0).getCurrency());
    assertEquals(mongoProductDtos.get(0).getName(), products.get(0).getName());
    assertEquals(mongoProductDtos.get(0).getPrice(), products.get(0).getPrice());
  }

  @Test
  void fromProductToMongoProductDto() {
    Product product = TestOjectBuilder.createProduct();
    var mongoProductDto = mongoProductDtoMapper.fromProductToMongoProductDto(product);
    assertEquals(product.getPrice(), mongoProductDto.getPrice());
    assertEquals(product.getCurrency(), mongoProductDto.getCurrency());
    assertEquals(product.getName(), mongoProductDto.getName());
    assertEquals(product.getId(), mongoProductDto.getId());
  }
}
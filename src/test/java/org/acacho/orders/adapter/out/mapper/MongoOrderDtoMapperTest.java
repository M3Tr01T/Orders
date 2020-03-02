package org.acacho.orders.adapter.out.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;
import org.acacho.orders.TestOjectBuilder;
import org.acacho.orders.adapter.out.dto.MongoOrderDto;
import org.acacho.orders.boot.OrderApp;
import org.acacho.orders.domain.entity.Order;
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
class MongoOrderDtoMapperTest {

  @Autowired
  private ModelMapper modelMapper;
  @Autowired
  MongoOrderDtoMapper mongoOrderDtoMapper;

  @Test
  void fromMongoOrderDtoToOrder() {
    MongoOrderDto mongoOrderDto = TestOjectBuilder.createMongoOrderDto();
    var order = mongoOrderDtoMapper.fromMongoOrderDtoToOrder(mongoOrderDto);
    assertEquals(mongoOrderDto.getId(), order.getId());
    assertEquals(mongoOrderDto.getCreatedAt(), order.getCreatedAt());
    assertEquals(mongoOrderDto.getEmail(), order.getEmail());
  }

  @Test
  void fromMongoOrderDtosToOrders() {
    List<MongoOrderDto> mongoOrderDtos = Collections
        .singletonList(TestOjectBuilder.createMongoOrderDto());
    var orders = mongoOrderDtoMapper.fromMongoOrderDtosToOrders(mongoOrderDtos);
    assertEquals(mongoOrderDtos.size(), orders.size());
    assertEquals(mongoOrderDtos.get(0).getId(), orders.get(0).getId());
    assertEquals(mongoOrderDtos.get(0).getEmail(), orders.get(0).getEmail());
    assertEquals(mongoOrderDtos.get(0).getCreatedAt(), orders.get(0).getCreatedAt());
  }

  @Test
  void fromOrderToMongoOrderDto() {
    Order order = TestOjectBuilder.createOrder();
    var mongoOrderDto = mongoOrderDtoMapper.fromOrderToMongoOrderDto(order);
    assertEquals(order.getId(), mongoOrderDto.getId());
    assertEquals(order.getCreatedAt(), mongoOrderDto.getCreatedAt());
    assertEquals(order.getEmail(), mongoOrderDto.getEmail());
  }
}
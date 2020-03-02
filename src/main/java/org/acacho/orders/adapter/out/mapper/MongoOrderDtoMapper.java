package org.acacho.orders.adapter.out.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.acacho.orders.adapter.out.dto.MongoOrderDto;
import org.acacho.orders.domain.entity.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MongoOrderDtoMapper {

  @Autowired
  private ModelMapper modelMapper;

  public Order fromMongoOrderDtoToOrder(MongoOrderDto mongoOrderDto) {
    var mapped = modelMapper.map(mongoOrderDto, Order.class);
    return mapped;
  }

  public List<Order> fromMongoOrderDtosToOrders(
      List<MongoOrderDto> mongoOrderDtos) {
    return mongoOrderDtos.stream()
        .map(this::fromMongoOrderDtoToOrder)
        .collect(Collectors.toList());
  }

  public MongoOrderDto fromOrderToMongoOrderDto(Order order) {
    return modelMapper.map(order, MongoOrderDto.class);
  }
}

package org.acacho.orders.adapter.in.mapper;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.acacho.orders.adapter.in.dto.OrderRestInDto;
import org.acacho.orders.adapter.out.dto.OrderRestOutDto;
import org.acacho.orders.domain.entity.Order;
import org.acacho.orders.domain.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {

  private final ModelMapper modelMapper;
  private final ProductMapper productMapper;

  public OrderRestOutDto fromOrderToOrderRestOutDto(Order order) {
    var mapped = modelMapper.map(order, OrderRestOutDto.class)
        .withTotalPrice(order.getTotalPrice());
    return mapped;
  }

  public List<OrderRestOutDto> fromOrdersToOrderRestOutDtos(List<Order> order) {
    return order.stream()
        .map(this::fromOrderToOrderRestOutDto)
        .collect(Collectors.toList());
  }

  public Order fromOrderRestInDtoToOrder(OrderRestInDto orderRestInDto) {
    return Order.builder()
        .email(orderRestInDto.getEmail())
        .products(orderRestInDto.getProductIds().stream()
            .map(id -> Product.builder().id(id).build())
            .collect(Collectors.toList()))
        .build();
  }
}

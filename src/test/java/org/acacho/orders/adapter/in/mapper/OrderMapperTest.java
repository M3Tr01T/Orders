package org.acacho.orders.adapter.in.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.acacho.orders.TestOjectBuilder;
import org.acacho.orders.adapter.in.dto.OrderRestInDto;
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
class OrderMapperTest {

  @Autowired
  private ModelMapper modelMapper;
  @Autowired
  private ProductMapper productMapper;
  @Autowired
  OrderMapper orderMapper;

  @Test
  void fromOrderToOrderRestOutDto() {
    Order order = TestOjectBuilder.createOrder();
    var orderRestOutDto = orderMapper.fromOrderToOrderRestOutDto(order);
    assertEquals(order.getEmail(), orderRestOutDto.getEmail());
    assertEquals(order.getCreatedAt(), orderRestOutDto.getCreatedAt());
    assertEquals(order.getTotalPrice(), orderRestOutDto.getTotalPrice());
    assertEquals(order.getProducts().size(), orderRestOutDto.getProducts().size());
    var expectedProduct = order.getProducts().get(0);
    var actualProduct = orderRestOutDto.getProducts().get(0);
    assertEquals(expectedProduct.getName(), actualProduct.getName());
    assertEquals(expectedProduct.getCurrency(), actualProduct.getCurrency());
    assertEquals(expectedProduct.getId(), actualProduct.getId());
    assertEquals(expectedProduct.getPrice(), actualProduct.getPrice());
  }

  @Test
  void fromOrdersToOrderRestOutDtos() {
    List<Order> orders = TestOjectBuilder.createOrderList();
    var orderRestOutDtos = orderMapper.fromOrdersToOrderRestOutDtos(orders);
    assertEquals(orders.size(), orderRestOutDtos.size());
    assertEquals(orders.get(0).getTotalPrice(), orderRestOutDtos.get(0).getTotalPrice());
    assertEquals(orders.get(0).getId(), orderRestOutDtos.get(0).getId());
    assertEquals(orders.get(0).getCreatedAt(), orderRestOutDtos.get(0).getCreatedAt());
    assertEquals(orders.get(0).getTotalPrice(), orderRestOutDtos.get(0).getTotalPrice());
  }

  @Test
  void fromOrderRestInDtoToOrder() {
    OrderRestInDto orderRestInDto = TestOjectBuilder.createOrderRestInDto();
    var order = orderMapper.fromOrderRestInDtoToOrder(orderRestInDto);
    assertEquals(orderRestInDto.getEmail(), order.getEmail());
    assertEquals(orderRestInDto.getProductIds().size(), order.getProducts().size());
    for (int i = 0; i < order.getProducts().size(); i++) {
      assertEquals(orderRestInDto.getProductIds().get(i), order.getProducts().get(i).getId());
    }
  }
}
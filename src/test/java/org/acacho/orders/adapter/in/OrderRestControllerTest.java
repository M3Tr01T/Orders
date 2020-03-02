package org.acacho.orders.adapter.in;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.acacho.orders.TestOjectBuilder;
import org.acacho.orders.adapter.in.dto.OrderRestInDto;
import org.acacho.orders.adapter.in.mapper.OrderMapper;
import org.acacho.orders.domain.OrderService;
import org.acacho.orders.domain.exception.OrderNotFoundException;
import org.acacho.orders.domain.exception.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderRestControllerTest {

  @Mock
  private OrderService orderService;
  @Mock
  private OrderMapper orderMapper;
  @InjectMocks
  OrderRestController orderRestController;

  @Test
  void createOrder() throws ProductNotFoundException {
    OrderRestInDto orderRestInDto = TestOjectBuilder.createOrderRestInDto();
    orderRestController.createOrder(orderRestInDto);
    verify(orderMapper).fromOrderRestInDtoToOrder(orderRestInDto);
    verify(orderService).placerOrder(any());
    verify(orderMapper).fromOrderToOrderRestOutDto(any());
  }

  @Test
  void retrieveAllOrders() throws OrderNotFoundException {
    orderRestController.retrieveAllOrders(null, null);
    verify(orderService).getAll(any(), any());
    verify(orderMapper).fromOrdersToOrderRestOutDtos(any());
  }
}
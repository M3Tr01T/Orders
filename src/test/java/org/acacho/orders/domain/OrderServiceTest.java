package org.acacho.orders.domain;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import org.acacho.orders.TestOjectBuilder;
import org.acacho.orders.domain.entity.Order;
import org.acacho.orders.domain.exception.ProductNotFoundException;
import org.acacho.orders.domain.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

  @Mock
  private OrderRepository orderRepository;
  @Mock
  private ProductService productService;
  @InjectMocks
  OrderService orderService;

  @Test
  void placerOrder() throws ProductNotFoundException {
    when(productService.findAllByIds(any())).thenReturn(TestOjectBuilder.createProductList());
    Order order = TestOjectBuilder.createOrder();
    orderService.placerOrder(order);
    verify(orderRepository).save(any());
  }

  @Test
  void getAll() {
    var start = LocalDateTime.now();
    var end = LocalDateTime.now().plusMonths(1);
    orderService.getAll(start, end);
    verify(orderRepository).findAllBetween(start, end);
  }
}
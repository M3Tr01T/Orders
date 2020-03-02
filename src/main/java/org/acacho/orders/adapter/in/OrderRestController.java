package org.acacho.orders.adapter.in;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.acacho.orders.adapter.in.dto.OrderRestInDto;
import org.acacho.orders.adapter.in.mapper.OrderMapper;
import org.acacho.orders.adapter.out.dto.OrderRestOutDto;
import org.acacho.orders.domain.OrderService;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
public class OrderRestController implements OrderRest {

  private final OrderService orderService;
  private final OrderMapper orderMapper;

  @Override
  public OrderRestOutDto createOrder(OrderRestInDto orderRestInDto) {
    log.info("Received POST call to /order");
    var order = orderService.placerOrder(orderMapper.fromOrderRestInDtoToOrder(orderRestInDto));
    return orderMapper.fromOrderToOrderRestOutDto(order);
  }

  @Override
  public List<OrderRestOutDto> retrieveAllOrders(LocalDateTime startDate,
      LocalDateTime endDate) {
    return orderMapper.fromOrdersToOrderRestOutDtos(orderService.getAll(startDate, endDate));
  }
}

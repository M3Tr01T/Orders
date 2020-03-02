package org.acacho.orders.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.acacho.orders.domain.entity.Order;

public interface OrderRepository {

  Optional<Order> findById(String key);

  Order save(Order order);

  List<Order> findAllBetween(LocalDateTime startDate, LocalDateTime endDate);
}

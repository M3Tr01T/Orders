package org.acacho.orders.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.acacho.orders.domain.entity.Order;
import org.acacho.orders.domain.entity.Product;
import org.acacho.orders.domain.repository.OrderRepository;

@Slf4j
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final ProductService productService;

  /**
   * Creates a new order.
   *
   * @param order new order to create
   * @return the new generated order
   */
  public Order placerOrder(Order order) {
    var products = productService
        .findAllByIds(order.getProducts().stream()
            .map(Product::getId)
            .collect(Collectors.toList()));

    order.setProducts(products);
    order = orderRepository.save(order);
    return order;
  }

  /**
   * retrieves all orders with a creation date between the passed values. dates ignored if null.
   *
   * @param start start date
   * @param end   end date
   * @return list of orders
   */
  public List<Order> getAll(LocalDateTime start, LocalDateTime end) {
    return orderRepository.findAllBetween(start, end);
  }
}

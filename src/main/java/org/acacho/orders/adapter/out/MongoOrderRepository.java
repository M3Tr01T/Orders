package org.acacho.orders.adapter.out;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.acacho.orders.adapter.out.dto.MongoOrderDto;
import org.acacho.orders.adapter.out.mapper.MongoOrderDtoMapper;
import org.acacho.orders.domain.entity.Order;
import org.acacho.orders.domain.repository.OrderRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MongoOrderRepository implements OrderRepository {

  private final SpringMongoOrderRepository springMongoOrderRepository;
  private final MongoOrderDtoMapper mongoOrderDtoMapper;
  private final MongoTemplate mongoTemplate;

  /**
   * retrieves an order for an specific id.
   *
   * @param key id to search for
   * @return an optional of the order, or empty if order is not found
   */
  public Optional<Order> findById(String key) {
    var mongoOrderDtoOptional = springMongoOrderRepository.findById(key);
    if (mongoOrderDtoOptional.isPresent()) {
      log.info("Order with id {} retrieved successfully", key);
      var mongoOrderDto = mongoOrderDtoOptional.get();
      return Optional.of(mongoOrderDtoMapper.fromMongoOrderDtoToOrder(mongoOrderDto));
    }
    log.info("Order with id {} could not be found", key);
    return Optional.empty();
  }

  /**
   * saves an order.
   *
   * @param order object to save.
   * @return saved object.
   */
  public Order save(Order order) {
    var savedMongoOrder = springMongoOrderRepository
        .save(mongoOrderDtoMapper.fromOrderToMongoOrderDto(order));
    log.info("Order with id {} saved successfully", savedMongoOrder.getId());
    return mongoOrderDtoMapper.fromMongoOrderDtoToOrder(savedMongoOrder);
  }

  /**
   * find all orders between the start and the end date passed. Those values are ignored if null.
   *
   * @param startDate start date.
   * @param endDate   end date.
   * @return list of orders.
   */
  public List<Order> findAllBetween(LocalDateTime startDate, LocalDateTime endDate) {
    var orderDtos = findByCreatedAtBetween(startDate, endDate);
    log.info("Retrieved all orders from DB created between {} and {}", startDate, endDate);
    return mongoOrderDtoMapper.fromMongoOrderDtosToOrders(orderDtos);
  }

  private List<MongoOrderDto> findByCreatedAtBetween(LocalDateTime startDate,
      LocalDateTime endDate) {
    if (startDate != null || endDate != null) {
      var criteria = Criteria.where("createdAt");
      if (startDate != null) {
        criteria.gte(startDate);
      }
      if (endDate != null) {
        criteria.lt(endDate);
      }
      var query = new Query().addCriteria(criteria);
      return mongoTemplate.find(query, MongoOrderDto.class);
    } else {
      return springMongoOrderRepository.findAll();
    }
  }
}

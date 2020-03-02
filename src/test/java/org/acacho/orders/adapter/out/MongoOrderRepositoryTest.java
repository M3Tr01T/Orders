package org.acacho.orders.adapter.out;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import org.acacho.orders.TestOjectBuilder;
import org.acacho.orders.adapter.out.mapper.MongoOrderDtoMapper;
import org.acacho.orders.domain.entity.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;

@ExtendWith(MockitoExtension.class)
class MongoOrderRepositoryTest {

  @Mock
  private SpringMongoOrderRepository springMongoOrderRepository;
  @Mock
  private MongoOrderDtoMapper mongoOrderDtoMapper;
  @Mock
  private MongoTemplate mongoTemplate;
  @InjectMocks
  MongoOrderRepository mongoOrderRepository;

  @Test
  void findById() {
    when(springMongoOrderRepository.findById(any()))
        .thenReturn(Optional.of(TestOjectBuilder.createMongoOrderDto()));
    when(mongoOrderDtoMapper.fromMongoOrderDtoToOrder(any()))
        .thenReturn(TestOjectBuilder.createOrder());
    var order = mongoOrderRepository.findById("id");
    assertTrue(order.isPresent());
  }

  @Test
  void findByIdEmpty() {
    var order = mongoOrderRepository.findById("id");
    assertTrue(order.isEmpty());
  }

  @Test
  void save() {
    when(springMongoOrderRepository.save(any())).thenReturn(TestOjectBuilder.createMongoOrderDto());
    Order order = TestOjectBuilder.createOrder();
    order = mongoOrderRepository.save(order);
  }

  @Test
  void findAllBetween() {
    var start = LocalDateTime.now();
    var end = LocalDateTime.now().plusMonths(1);
    mongoOrderRepository.findAllBetween(start, end);
    verify(mongoTemplate).find(any(), any());
  }

  @Test
  void findAllBetweenWithNull() {
    mongoOrderRepository.findAllBetween(null, null);
    verify(springMongoOrderRepository).findAll();
  }
}
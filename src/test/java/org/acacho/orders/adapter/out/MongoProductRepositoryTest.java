package org.acacho.orders.adapter.out;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;
import org.acacho.orders.TestOjectBuilder;
import org.acacho.orders.adapter.out.mapper.MongoProductDtoMapper;
import org.acacho.orders.domain.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MongoProductRepositoryTest {

  @Mock
  private SpringMongoProductRepository springMongoProductRepository;
  @Mock
  private MongoProductDtoMapper mongoProductDtoMapper;
  @InjectMocks
  MongoProductRepository mongoProductRepository;

  @Test
  void findById() {
    when(springMongoProductRepository.findById(any()))
        .thenReturn(Optional.of(TestOjectBuilder.createMongoProductDto()));
    when(mongoProductDtoMapper.fromMongoProductDtoToProduct(any()))
        .thenReturn(TestOjectBuilder.createProduct());
    var product = mongoProductRepository.findById("id");
    assertTrue(product.isPresent());
  }

  @Test
  void findByIdEmpty() {
    var product = mongoProductRepository.findById("id");
    assertTrue(product.isEmpty());
  }

  @Test
  void save() {
    when(springMongoProductRepository.save(any()))
        .thenReturn(TestOjectBuilder.createMongoProductDto());
    Product product = TestOjectBuilder.createProduct();
    mongoProductRepository.save(product);
  }

  @Test
  void findAll() {
    mongoProductRepository.findAll();
  }

  @Test
  void findAllByIds() {
    mongoProductRepository.findAllByIds(Arrays.asList("ID1", "ID2", "ID3"));
  }
}
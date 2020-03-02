package org.acacho.orders.adapter.out;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.acacho.orders.adapter.out.dto.MongoProductDto;
import org.acacho.orders.adapter.out.mapper.MongoProductDtoMapper;
import org.acacho.orders.domain.entity.Product;
import org.acacho.orders.domain.repository.ProductRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MongoProductRepository implements ProductRepository {

  private final SpringMongoProductRepository springMongoProductRepository;
  private final MongoProductDtoMapper mongoProductDtoMapper;

  public Optional<Product> findById(String key) {
    var mongoProductDtoOptional = springMongoProductRepository.findById(key);
    if (mongoProductDtoOptional.isPresent()) {
      log.info("Product with id {} retrieved successfully", key);
      var mongoProductDto = mongoProductDtoOptional.get();
      return Optional.of(mongoProductDtoMapper.fromMongoProductDtoToProduct(mongoProductDto));
    }
    log.info("Product with id {} could not be found", key);
    return Optional.empty();
  }

  public Product save(Product product) {
    var savedMongoProduct = springMongoProductRepository
        .save(mongoProductDtoMapper.fromProductToMongoProductDto(product));
    log.info("Product with id {} saved successfully", product.getId());
    return mongoProductDtoMapper.fromMongoProductDtoToProduct(savedMongoProduct);
  }

  public List<Product> findAll() {
    var productDtos = springMongoProductRepository.findAll();
    log.info("Retrieved all products from DB");
    return mongoProductDtoMapper.fromMongoProductDtosToProducts(productDtos);
  }

  public List<Product> findAllByIds(List<String> ids) {
    var productDtos = (List<MongoProductDto>) springMongoProductRepository.findAllById(ids);
    log.info("Retrieved products from DB");
    return mongoProductDtoMapper.fromMongoProductDtosToProducts(productDtos);
  }
}

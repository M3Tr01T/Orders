package org.acacho.orders.domain.repository;

import java.util.List;
import java.util.Optional;
import org.acacho.orders.domain.entity.Product;

public interface ProductRepository {

  Optional<Product> findById(String key);

  Product save(Product product);

  List<Product> findAll();

  List<Product> findAllByIds(List<String> ids);
}

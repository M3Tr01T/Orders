package org.acacho.orders.boot.config;

import org.acacho.orders.domain.OrderService;
import org.acacho.orders.domain.ProductService;
import org.acacho.orders.domain.repository.OrderRepository;
import org.acacho.orders.domain.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class DefaultServiceConfig {

  @Bean
  public OrderService orderService(OrderRepository orderRepository, ProductService productService) {
    return new OrderService(orderRepository, productService);
  }

  @Bean
  public ProductService productService(ProductRepository productRepository) {
    return new ProductService(productRepository);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}

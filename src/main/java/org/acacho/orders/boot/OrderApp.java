package org.acacho.orders.boot;

import org.acacho.orders.adapter.out.SpringMongoOrderRepository;
import org.acacho.orders.adapter.out.SpringMongoProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"org.acacho.orders"})
@EnableMongoRepositories(basePackageClasses = {
    SpringMongoProductRepository.class,
    SpringMongoOrderRepository.class})
public class OrderApp {

  public static void main(String[] args) {
    SpringApplication.run(OrderApp.class, args);
  }
}

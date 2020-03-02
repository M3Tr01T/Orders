package org.acacho.orders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.experimental.UtilityClass;
import org.acacho.orders.adapter.in.dto.OrderRestInDto;
import org.acacho.orders.adapter.in.dto.ProductRestInDto;
import org.acacho.orders.adapter.out.dto.MongoOrderDto;
import org.acacho.orders.adapter.out.dto.MongoProductDto;
import org.acacho.orders.domain.entity.Order;
import org.acacho.orders.domain.entity.Product;

@UtilityClass
public class TestOjectBuilder {

  public static final String ORDER_ID = "ORDER_ID";
  public static final String PRODUCT_ID = "PRODUCT_ID";
  public static final String CURRENCY = "EUR";
  public static final String NAME = "NAME";
  public static final String EMAIL = "EMAIL@TEST.COM";
  public static final BigDecimal PRICE = BigDecimal.valueOf(199.99);

  public Order createOrder() {
    return Order.builder()
        .id(ORDER_ID)
        .email(EMAIL)
        .createdAt(LocalDateTime.now())
        .products(createProductList())
        .build();
  }

  public MongoOrderDto createMongoOrderDto() {
    return MongoOrderDto.builder()
        .id(ORDER_ID)
        .email(EMAIL)
        .createdAt(LocalDateTime.now())
        .products(createProductList())
        .build();
  }

  public OrderRestInDto createOrderRestInDto() {
    return OrderRestInDto.builder()
        .email(EMAIL)
        .productIds(Arrays.asList("ID_1", "ID_2", "ID_3"))
        .build();
  }

  public Product createProduct() {
    return Product.builder()
        .id(PRODUCT_ID)
        .currency(CURRENCY)
        .name(NAME)
        .price(PRICE)
        .build();
  }

  public ProductRestInDto createProductRestInDto() {
    return ProductRestInDto.builder()
        .currency(CURRENCY)
        .name(NAME)
        .price(PRICE)
        .build();
  }

  public MongoProductDto createMongoProductDto() {
    return MongoProductDto.builder()
        .currency(CURRENCY)
        .name(NAME)
        .price(PRICE)
        .build();
  }

  public List<Product> createProductList() {
    return Collections.singletonList(createProduct());
  }

  public List<Order> createOrderList() {
    return Collections.singletonList(createOrder());
  }

}

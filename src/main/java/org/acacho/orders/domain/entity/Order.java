package org.acacho.orders.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@With
public class Order {

  private String id;
  private List<Product> products;
  private String email;
  private LocalDateTime createdAt;

  public BigDecimal getTotalPrice() {
    return products.stream()
        .filter(product -> product.getPrice() != null)
        .map(Product::getPrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}

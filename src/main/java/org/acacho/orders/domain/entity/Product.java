package org.acacho.orders.domain.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  private String id;
  private String name;
  private BigDecimal price;
  private String currency;
}

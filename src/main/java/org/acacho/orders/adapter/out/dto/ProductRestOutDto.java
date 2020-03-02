package org.acacho.orders.adapter.out.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRestOutDto {

  private String id;
  private String name;
  private BigDecimal price;
  private String currency;
}

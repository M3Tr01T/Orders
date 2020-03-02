package org.acacho.orders.adapter.in.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRestInDto {

  @NotEmpty
  private String name;
  @NotNull
  private BigDecimal price;
  @NotEmpty
  private String currency;
}

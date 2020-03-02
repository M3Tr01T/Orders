package org.acacho.orders.adapter.out.dto;

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
public class OrderRestOutDto {

  private String id;
  private List<ProductRestOutDto> products;
  private String email;
  private LocalDateTime createdAt;
  private BigDecimal totalPrice;
}

package org.acacho.orders.adapter.out.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.acacho.orders.domain.entity.Product;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class MongoOrderDto {

  @Id
  private String id;
  private List<Product> products;
  private String email;
  @CreatedDate
  private LocalDateTime createdAt;
}

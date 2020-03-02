package org.acacho.orders.adapter.in.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.acacho.orders.adapter.in.dto.ProductRestInDto;
import org.acacho.orders.adapter.out.dto.ProductRestOutDto;
import org.acacho.orders.domain.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  @Autowired
  private ModelMapper modelMapper;

  public ProductRestOutDto fromProductToProductRestOutDto(Product product) {
    return modelMapper.map(product, ProductRestOutDto.class);
  }

  public Product fromProductRestInDtoToProduct(ProductRestInDto productRestInDto) {
    return modelMapper.map(productRestInDto, Product.class);
  }

  public List<Product> fromProductRestInDtosToProducts(List<ProductRestInDto> productRestInDtos) {
    return productRestInDtos.stream()
        .map(this::fromProductRestInDtoToProduct)
        .collect(Collectors.toList());
  }

  public List<ProductRestOutDto> fromProductsToProductRestOutDtos(List<Product> products) {
    return products.stream()
        .map(this::fromProductToProductRestOutDto)
        .collect(Collectors.toList());
  }
}

package org.acacho.orders.adapter.in;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.acacho.orders.adapter.in.dto.ProductRestInDto;
import org.acacho.orders.adapter.in.mapper.ProductMapper;
import org.acacho.orders.adapter.out.dto.ProductRestOutDto;
import org.acacho.orders.domain.ProductService;
import org.acacho.orders.domain.exception.ProductNotFoundException;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
public class ProductRestController implements ProductRest {

  private final ProductService productService;
  private final ProductMapper productMapper;

  @Override
  public ProductRestOutDto createProduct(ProductRestInDto productRestInDto) {
    log.info("Received POST call to /product");
    var product = productService.createProduct(productMapper.fromProductRestInDtoToProduct(
        productRestInDto));
    return productMapper.fromProductToProductRestOutDto(product);
  }

  @Override
  public ProductRestOutDto updateProduct(String productId, ProductRestInDto productRestInDto)
      throws ProductNotFoundException {
    log.info("Received PUT call to /product/{}", productId);
    var product = productMapper.fromProductRestInDtoToProduct(productRestInDto).withId(productId);
    productService.updateProduct(product);
    return productMapper.fromProductToProductRestOutDto(product);
  }

  @Override
  public List<ProductRestOutDto> retrieveAllProducts() {
    return productMapper.fromProductsToProductRestOutDtos(productService.getAllProducts());
  }
}

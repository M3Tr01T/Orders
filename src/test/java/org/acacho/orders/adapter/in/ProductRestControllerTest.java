package org.acacho.orders.adapter.in;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.acacho.orders.TestOjectBuilder;
import org.acacho.orders.adapter.in.dto.ProductRestInDto;
import org.acacho.orders.adapter.in.mapper.ProductMapper;
import org.acacho.orders.domain.ProductService;
import org.acacho.orders.domain.exception.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductRestControllerTest {

  @Mock
  private ProductService productService;
  @Mock
  private ProductMapper productMapper;
  @InjectMocks
  ProductRestController productRestController;

  @Test
  void createProduct() {
    ProductRestInDto productRestInDto = TestOjectBuilder.createProductRestInDto();
    productRestController.createProduct(productRestInDto);
    verify(productMapper).fromProductRestInDtoToProduct(productRestInDto);
    verify(productService).createProduct(any());
    verify(productMapper).fromProductToProductRestOutDto(any());
  }

  @Test
  void updateProduct() throws ProductNotFoundException {
    when(productMapper.fromProductRestInDtoToProduct(any()))
        .thenReturn(TestOjectBuilder.createProduct());
    ProductRestInDto productRestInDto = TestOjectBuilder.createProductRestInDto();
    productRestController.updateProduct("id", productRestInDto);
    verify(productMapper).fromProductRestInDtoToProduct(productRestInDto);
    verify(productService).updateProduct(any());
  }

  @Test
  void retrieveAllProducts() throws ProductNotFoundException {
    productRestController.retrieveAllProducts();
    verify(productService).getAllProducts();
    verify(productMapper).fromProductsToProductRestOutDtos(any());
  }
}
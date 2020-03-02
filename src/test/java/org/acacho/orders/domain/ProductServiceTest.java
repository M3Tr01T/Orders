package org.acacho.orders.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.acacho.orders.TestOjectBuilder;
import org.acacho.orders.domain.entity.Product;
import org.acacho.orders.domain.exception.ProductNotFoundException;
import org.acacho.orders.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @Mock
  private ProductRepository productRepository;
  @InjectMocks
  ProductService productService;

  @Test
  void createProduct() {
    Product product = TestOjectBuilder.createProduct();
    productService.createProduct(product);
    verify(productRepository).save(any());
  }

  @Test
  void updateProduct() throws ProductNotFoundException {
    Product product = TestOjectBuilder.createProduct();
    when(productRepository.findById(any())).thenReturn(Optional.of(product));
    productService.updateProduct(product.withName("NEW_NAME"));
    verify(productRepository).save(any());
  }

  @Test
  void updateProductSameValues() throws ProductNotFoundException {
    Product product = TestOjectBuilder.createProduct();
    when(productRepository.findById(any())).thenReturn(Optional.of(product));
    productService.updateProduct(product);
    verify(productRepository, times(0)).save(any());
  }

  @Test
  void updateProductNotExistingThrowsException() {
    Product product = TestOjectBuilder.createProduct();
    assertThrows(ProductNotFoundException.class, () -> {
      productService.updateProduct(product);
    });
  }

  @Test
  void getAllProducts() {
    productService.getAllProducts();
    verify(productRepository).findAll();
  }
}
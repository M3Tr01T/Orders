package org.acacho.orders.adapter.in.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;
import org.acacho.orders.TestOjectBuilder;
import org.acacho.orders.adapter.in.dto.ProductRestInDto;
import org.acacho.orders.boot.OrderApp;
import org.acacho.orders.domain.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ActiveProfiles("test")
@SpringBootTest(classes = {OrderApp.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class ProductMapperTest {

  @Autowired
  private ModelMapper modelMapper;
  @Autowired
  private ProductMapper productMapper;

  @Test
  void fromProductToProductRestOutDto() {
    Product product = TestOjectBuilder.createProduct();
    var productRestOutDto = productMapper.fromProductToProductRestOutDto(product);
    assertEquals(product.getPrice(), productRestOutDto.getPrice());
    assertEquals(product.getCurrency(), productRestOutDto.getCurrency());
    assertEquals(product.getName(), productRestOutDto.getName());
    assertEquals(product.getId(), productRestOutDto.getId());
  }

  @Test
  void fromProductRestInDtoToProduct() {
    ProductRestInDto productRestInDto = TestOjectBuilder.createProductRestInDto();
    var product = productMapper.fromProductRestInDtoToProduct(productRestInDto);
    assertEquals(productRestInDto.getPrice(), product.getPrice());
    assertEquals(productRestInDto.getCurrency(), product.getCurrency());
    assertEquals(productRestInDto.getName(), product.getName());
  }

  @Test
  void fromProductRestInDtosToProducts() {
    var productRestInDtos = Collections.singletonList(TestOjectBuilder.createProductRestInDto());
    var products = productMapper.fromProductRestInDtosToProducts(productRestInDtos);
    assertEquals(productRestInDtos.size(), products.size());
    assertEquals(productRestInDtos.get(0).getCurrency(), products.get(0).getCurrency());
    assertEquals(productRestInDtos.get(0).getName(), products.get(0).getName());
    assertEquals(productRestInDtos.get(0).getPrice(), products.get(0).getPrice());
  }

  @Test
  void fromProductsToProductRestOutDtos() {
    List<Product> products = TestOjectBuilder.createProductList();
    var productRestOutDtos = productMapper.fromProductsToProductRestOutDtos(products);
    assertEquals(products.size(), productRestOutDtos.size());
    assertEquals(products.get(0).getId(), productRestOutDtos.get(0).getId());
    assertEquals(products.get(0).getPrice(), productRestOutDtos.get(0).getPrice());
    assertEquals(products.get(0).getCurrency(), productRestOutDtos.get(0).getCurrency());
    assertEquals(products.get(0).getName(), productRestOutDtos.get(0).getName());
  }
}
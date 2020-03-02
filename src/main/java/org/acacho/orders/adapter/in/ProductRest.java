package org.acacho.orders.adapter.in;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import org.acacho.orders.adapter.in.dto.ProductRestInDto;
import org.acacho.orders.adapter.out.dto.ProductRestOutDto;
import org.acacho.orders.domain.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Api(tags = {"product"}, value = "/product")
public interface ProductRest {

  int OK = 200;
  int CREATED = 201;
  int ACCEPTED = 202;
  int INTERNAL_SERVER_ERROR = 500;
  int BAD_REQUEST = 400;
  int NOT_FOUND = 404;

  @ApiOperation(value = "Create product")
  @ApiResponses(value = {
      @ApiResponse(code = CREATED, message = "Product created", response = String.class),
      @ApiResponse(code = INTERNAL_SERVER_ERROR, message = "Internal Server Error",
          response = String.class)})
  @PostMapping(path = "/product", produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  ProductRestOutDto createProduct(@Valid @RequestBody ProductRestInDto productRestInDto);

  @ApiOperation(value = "Update product")
  @ApiResponses(value = {
      @ApiResponse(code = ACCEPTED, message = "Product updated", response = String.class),
      @ApiResponse(code = BAD_REQUEST, message = "Bad request", response = String.class),
      @ApiResponse(code = NOT_FOUND, message = "Product not found", response = String.class),
      @ApiResponse(code = INTERNAL_SERVER_ERROR, message = "Internal Server Error",
          response = String.class)})
  @PutMapping(path = "/product/{productId}", consumes = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.ACCEPTED)
  ProductRestOutDto updateProduct(@PathVariable final String productId,
      @Valid @RequestBody ProductRestInDto productRestInDto)
      throws ProductNotFoundException;

  @ApiOperation(value = "Get all products")
  @ApiResponses(value = {
      @ApiResponse(code = OK, message = "Products retrieved"),
      @ApiResponse(code = BAD_REQUEST, message = "Bad request", response = String.class),
      @ApiResponse(code = NOT_FOUND, message = "Product not found", response = String.class),
      @ApiResponse(code = INTERNAL_SERVER_ERROR, message = "Internal Server Error",
          response = String.class)})
  @GetMapping(path = "/products", produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  List<ProductRestOutDto> retrieveAllProducts() throws ProductNotFoundException;
}

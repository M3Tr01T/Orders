package org.acacho.orders.adapter.in;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import org.acacho.orders.adapter.in.dto.OrderRestInDto;
import org.acacho.orders.adapter.out.dto.OrderRestOutDto;
import org.acacho.orders.domain.exception.OrderNotFoundException;
import org.acacho.orders.domain.exception.ProductNotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Api(tags = {"order"}, value = "/order")
public interface OrderRest {

  int OK = 200;
  int CREATED = 201;
  int INTERNAL_SERVER_ERROR = 500;
  int BAD_REQUEST = 400;
  int NOT_FOUND = 404;

  @ApiOperation(value = "Create order")
  @ApiResponses(value = {
      @ApiResponse(code = CREATED, message = "Order created", response = String.class),
      @ApiResponse(code = INTERNAL_SERVER_ERROR, message = "Internal Server Error",
          response = String.class)})
  @PostMapping(path = "/order", produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  OrderRestOutDto createOrder(@Valid @RequestBody OrderRestInDto orderRestInDto)
      throws ProductNotFoundException;

  @ApiOperation(value = "Get all orders")
  @ApiResponses(value = {
      @ApiResponse(code = OK, message = "Orders retrieved"),
      @ApiResponse(code = BAD_REQUEST, message = "Bad request", response = String.class),
      @ApiResponse(code = NOT_FOUND, message = "Order not found", response = String.class),
      @ApiResponse(code = INTERNAL_SERVER_ERROR, message = "Internal Server Error",
          response = String.class)})
  @GetMapping(path = "/orders", produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  List<OrderRestOutDto> retrieveAllOrders(
      @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE_TIME) final LocalDateTime startDate,
      @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE_TIME) final LocalDateTime endDate)
      throws OrderNotFoundException;
}

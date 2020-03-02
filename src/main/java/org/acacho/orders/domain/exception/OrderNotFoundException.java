package org.acacho.orders.domain.exception;

public class OrderNotFoundException extends Exception {

  public OrderNotFoundException(String message) {
    super(message);
  }
}

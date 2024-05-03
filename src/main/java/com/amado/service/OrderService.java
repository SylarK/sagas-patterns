package com.amado.service;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class OrderService {

  private Set<Long> orders = new HashSet<>();

  public void addOrder(Long orderId) {
    orders.add(orderId);
  }

  public void cancelOrder(Long orderId) {
    orders.remove(orderId);
  }

}

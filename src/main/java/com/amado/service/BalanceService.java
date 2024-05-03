package com.amado.service;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class BalanceService {
  private int totalBalance = 0;
  private Map<Long, Integer> order_value = new HashMap<>();

  public BalanceService() {
    totalBalance = 100;
  }

  public void addOrderValue(Long orderId, Integer value) {
    if(value > totalBalance) {
      throw new IllegalStateException("Insufficient balance");
    }
    order_value.put(orderId, value);
    totalBalance -= value;
  }

  public void cancelOrderValue(Long orderId) {
    Integer value = order_value.get(orderId);
    if(value == null) {
      throw new IllegalStateException("Order not found");
    }
    order_value.remove(orderId);
    totalBalance += value;
  }

  public int getTotalBalance() {
    return totalBalance;
  }

}

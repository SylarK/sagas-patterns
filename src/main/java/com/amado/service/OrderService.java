package com.amado.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class OrderService {

  private Set<Long> orders = new HashSet<>();

  @Inject
  BalanceService balanceService;

  public void addOrder(Long id, int value) {
    orders.add(id);
    try {
      balanceService.addOrderValue(id, value);
      System.out.println(
        "Order " + id + " with value " + value + " was registered. Available balance: "
          + balanceService.getTotalBalance());
    } catch (IllegalStateException e) {
      cancelOrder(id);
      System.err.println("Order " + id + " with value " + value + " was cancelled");
    }
  }

  public void cancelOrder(Long id) {
    orders.remove(id);
  }

}

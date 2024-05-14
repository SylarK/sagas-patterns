package pt.amado;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class BalanceService {

  private int balanceTotal;
  private final Map<Long, Integer> balancePerOrder = new HashMap<>();

  public BalanceService() {
    balanceTotal = 100;
  }

  public void addBalancePerOrder(Long orderId, Integer amount) {
    if(balanceTotal < amount) {
      throw new RuntimeException("Insufficient balance");
    }
    balancePerOrder.put(orderId, amount);
    balanceTotal -= amount;
  }

  public void cancelOrder(Long orderId) {
    System.out.println("OrderValue failed. Starting order cancel...");
  }

  public int getBalanceTotal() {
    return balanceTotal;
  }
}

package pt.amado;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class BalanceService {

  private long balanceTotal;
  private Map<Long, Integer> balancePerOrder = new HashMap<>();

  public BalanceService() {
    balanceTotal = 100;
  }

  public void addOrder(Long orderId, Integer amount) {
    if(balanceTotal < amount) {
      throw new RuntimeException("Insufficient balance");
    }
    balancePerOrder.put(orderId, amount);
    balanceTotal -= amount;
  }

  public void cancelOrder(Long orderId) {
    Integer amount = balancePerOrder.get(orderId);
    if(amount == null) {
      throw new RuntimeException("Order not found");
    }
    balancePerOrder.remove(orderId);
    balanceTotal += amount;
  }

  public long getBalanceTotal() {
    return balanceTotal;
  }
}

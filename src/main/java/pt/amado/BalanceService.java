package pt.amado;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;
import org.apache.camel.Header;

@ApplicationScoped
public class BalanceService {

  private int balanceTotal;
  private final Map<Long, Integer> balancePerOrder = new HashMap<>();

  public BalanceService() {
    balanceTotal = 100;
  }

  public void addBalancePerOrder(@Header("orderId") Long orderId, @Header("amount") Integer amount) {
    if(balanceTotal < amount) {
      throw new RuntimeException("Insufficient balance");
    }
    balancePerOrder.put(orderId, amount);
    balanceTotal -= amount;
  }

  public void cancelOrder(@Header("orderId") Long orderId) {
    System.out.println("OrderValue failed. Starting order cancel...");
  }

  public int getBalanceTotal() {
    return balanceTotal;
  }
}

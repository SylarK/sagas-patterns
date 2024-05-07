package pt.amado;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class OrderService {

  private Set<Long> orders = new HashSet<>();

  public void addOrder(Long orderId) {
    orders.add(orderId);
  }


}
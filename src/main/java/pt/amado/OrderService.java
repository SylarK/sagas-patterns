package pt.amado;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashSet;
import java.util.Set;
import org.apache.camel.Header;

@ApplicationScoped
public class OrderService {

  private Set<Long> orders = new HashSet<>();

  public void addOrder(@Header("orderId") Long orderId) {
    orders.add(orderId);
  }

  public void cancelOrder(@Header("orderId") Long orderId) {
    orders.remove(orderId);
  }

}

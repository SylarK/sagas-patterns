package com.amado;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/orchestrated-purchase")
public class OrchestratedPurchaseResource {

  @Inject
  BalanceService balanceService;

  @Inject
  OrderService orderService;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response saga() {

    // initial balance = 100
    long id = 0L;

    purchase(++id, 20);
    purchase(++id, 30);
    purchase(++id, 30);
    purchase(++id, 25);

    return Response.ok().build();
  }

  private void purchase(Long orderId, Integer value) {
    orderService.addOrder(orderId);
    try {
      balanceService.addOrderValue(orderId, value);
      System.out.println("Order " + orderId + " with value " + value + " was registered");
    } catch (IllegalStateException e) {
      orderService.cancelOrder(orderId);
      System.err.println("Order " + orderId + " with value " + value + " was cancelled");
    }
  }
}

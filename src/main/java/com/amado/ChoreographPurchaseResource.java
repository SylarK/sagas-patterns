package com.amado;

import com.amado.service.OrderService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/choreograph-purchase")
public class ChoreographPurchaseResource {

  @Inject
  OrderService orderService;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response saga() {
    // initial balance = 100
    long id = 0L;

    orderService.addOrder(++id, 20);
    orderService.addOrder(++id, 30);
    orderService.addOrder(++id, 40);
    orderService.addOrder(++id, 15);

    return Response.ok().build();
  }

}

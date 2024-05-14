package pt.amado;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/order")
public class BalanceResource {

  @Inject
  BalanceService balanceService;

  @GET
  @Path("/addBalancePerOrder")
  @Produces(MediaType.TEXT_PLAIN)
  public void balance(Long orderId, int value) {
    balanceService.addBalancePerOrder(orderId, value);
  }

  @GET
  @Path("/cancelOrder")
  @Produces(MediaType.TEXT_PLAIN)
  public void cancelOrder(Long orderId) {
    balanceService.cancelOrder(orderId);
  }

  @GET
  @Path("/getBalanceTotal")
  @Produces(MediaType.TEXT_PLAIN)
  public int getBalanceTotal() {
    return balanceService.getBalanceTotal();
  }

}

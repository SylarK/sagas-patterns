package pt.amado;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.camel.CamelContext;

@Path("/order")
public class OrderCamelResource {

  @Inject
  CamelContext context;

  @Path("/test")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response sagaOrder() {

    try{
      Long id = 0L;

      purchaseOrder(++id, 20);
      purchaseOrder(++id, 30);
      purchaseOrder(++id, 30);
      purchaseOrder(++id, 20);

      return Response.ok().entity("All good").build();

    } catch (Exception e){
      return Response.status(500).entity("Something went wrong: " + e.getMessage()).build();
    }
  }

  private void purchaseOrder(Long id, int value) {
    context.createFluentProducerTemplate()
      .to("direct:saga")
        .withHeader("orderId", id)
        .withHeader("id", id)
        .withHeader("amount", value)
      .request();
  }

}

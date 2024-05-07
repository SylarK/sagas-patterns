package pt.amado;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.SagaPropagation;
import org.apache.camel.saga.CamelSagaService;
import org.apache.camel.saga.InMemorySagaService;

@ApplicationScoped
public class SagaRoute extends RouteBuilder {

  @Inject
  private BalanceService balanceService;

  @Inject
  private OrderService orderService;

  @Override
  public void configure() throws Exception {

    CamelSagaService sagaService = new InMemorySagaService();
    getContext().addService(sagaService);

    from("direct:saga")
      .saga().propagation(SagaPropagation.REQUIRES_NEW).log("Saga started")
      .to("direct:createOrder").log("Creating order")
      .to("direct:addBalancePerOrder").log("Reserving balance")
      .to("direct:finishOrder").log("Finishing order");

    //OrderService
    from("direct:createOrder")
      .saga().propagation(SagaPropagation.MANDATORY)
        .compensation("direct:cancelOrder")
      .transform().header(Exchange.SAGA_LONG_RUNNING_ACTION)
      .bean(orderService, "addOrder").log("Order ${body} created");

    from("direct:cancelOrder")
      .transform().header(Exchange.SAGA_LONG_RUNNING_ACTION)
      .bean(orderService, "cancelOrder").log("Order ${body} cancelled");

    //BalanceService
    from("direct:addBalancePerOrder")
      .saga().propagation(SagaPropagation.MANDATORY)
        .compensation("direct:removeBalancePerOrder")
      .transform().header(Exchange.SAGA_LONG_RUNNING_ACTION)
      .bean(balanceService, "addBalancePerOrder").log("Balance ${header.amount} reserved for order $<header.orderId> (saga ${body})");

    from("direct:removeBalancePerOrder")
      .transform().header(Exchange.SAGA_LONG_RUNNING_ACTION)
      .bean(balanceService, "cancelOrder").log("Balance ${header.amount} cancelled for order $<header.orderId> (saga ${body})");

    //Finish Order
    from("direct:finishOrder")
      .saga().propagation(SagaPropagation.MANDATORY)
      .choice()
        .when(header("fail").isEqualTo(true))
        .to("saga:compensate");

  }

}

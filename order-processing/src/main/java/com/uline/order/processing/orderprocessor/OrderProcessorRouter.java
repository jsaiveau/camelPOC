package com.uline.order.processing.orderprocessor;

import java.io.InputStream;

import org.apache.camel.model.RoutesDefinition;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.uline.order.processing.AbstractRouteBuilder;

@Component
public class OrderProcessorRouter extends AbstractRouteBuilder {
	@Override
	public void configure() throws Exception {
		super.configure();

		// servlet is configured in Application.java
		restConfiguration().component("servlet").bindingMode(RestBindingMode.json);

		rest("/orderprocessor").get("/placeOrder").outType(OrderProcessor.class).to("direct:placeOrder");

		// Using Camel XML
		InputStream is = getClass().getResourceAsStream("order-processor-routes.xml");
		RoutesDefinition routes = getContext().loadRoutesDefinition(is);
		getContext().addRouteDefinitions(routes.getRoutes());

		// Using Java DSL
		// from("direct:placeOrder").loadBalance()
		// .circuitBreaker(2, 1000L, Exception.class)
		// .to("mock:result");
		// from("direct:placeOrder")
		// .process(exchange -> {
		// OrderProcessor hw = new OrderProcessor();
		// hw.setMessage("Placing the Order");
		// exchange.getIn().setBody(hw);
		// });
	}
}
package com.uline.order.processing.order;

import java.io.InputStream;

import org.apache.camel.model.RoutesDefinition;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.uline.order.processing.AbstractRouteBuilder;

@Component
public class OrderRouter extends AbstractRouteBuilder {

	@Override
	public void configure() throws Exception {
		super.configure();

		// servlet is configured in Application.java
		restConfiguration().component("servlet").bindingMode(RestBindingMode.json);

		rest("/order").get("/getAll").outType(Order.class).to("direct:getAllOrders");

		// Using Camel XML
		InputStream is = getClass().getResourceAsStream("order-routes.xml");
		RoutesDefinition routes = getContext().loadRoutesDefinition(is);
		getContext().addRouteDefinitions(routes.getRoutes());

		// Using Java DSL
		// from("direct:getAllOrders").process(exchange -> {
		// Order hw = new Order();
		// hw.setMessage("Retrieving Orders");
		// exchange.getIn().setBody(hw);
		// });
	}
}

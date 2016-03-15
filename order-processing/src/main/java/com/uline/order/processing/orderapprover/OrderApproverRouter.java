package com.uline.order.processing.orderapprover;

import java.io.InputStream;

import org.apache.camel.model.RoutesDefinition;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.uline.order.processing.AbstractRouteBuilder;
import com.uline.order.processing.order.Order;

@Component
public class OrderApproverRouter extends AbstractRouteBuilder {
	@Override
	public void configure() throws Exception {
		super.configure();

		// servlet is configured in Application.java
		restConfiguration().component("servlet").bindingMode(RestBindingMode.json);

		rest("/orderapprover").get("/approve").outType(Order.class).to("direct:approve");

		// Using Camel XML
		InputStream is = getClass().getResourceAsStream("order-approver-routes.xml");
		RoutesDefinition routes = getContext().loadRoutesDefinition(is);
		getContext().addRouteDefinitions(routes.getRoutes());

		// Using Java DSL
		// from("direct:approve")
		// .process(exchange -> {
		// OrderApprover hw = new OrderApprover();
		// hw.setMessage("Approving the Order");
		// exchange.getIn().setBody(hw);
		// });
	}
}
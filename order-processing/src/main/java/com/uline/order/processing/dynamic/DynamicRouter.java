package com.uline.order.processing.dynamic;

import java.io.InputStream;

import org.apache.camel.model.RoutesDefinition;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uline.order.processing.AbstractRouteBuilder;

@Component
public class DynamicRouter extends AbstractRouteBuilder {
	@Autowired
	RouteService routeService;

	@Override
	public void configure() throws Exception {
		super.configure();
		// servlet is configured in Application.java
		restConfiguration().component("servlet").bindingMode(RestBindingMode.json);

		rest("/routes").put().consumes("application/xml").type(Routes.class).to("direct:updateRoutes");
		rest("/routes").get().to("direct:getRoutes");

		// Using Camel XML
		InputStream is = getClass().getResourceAsStream("routes-routes.xml");
		RoutesDefinition routes = getContext().loadRoutesDefinition(is);
		getContext().addRouteDefinitions(routes.getRoutes());

		// Using Java DSL
		// from("direct:updateRoutes").beanRef("routeService", "updateRoutes");
		// from("direct:getRoutes").beanRef("routeService",
		// "getRoutes").toString();
	}
}

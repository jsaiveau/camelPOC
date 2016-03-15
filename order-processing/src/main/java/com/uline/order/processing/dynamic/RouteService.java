package com.uline.order.processing.dynamic;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {
	@Autowired
	CamelContext camelContext;

	public RouteService() {
	}

	public List<String> getRoutes() {
		List<String> retList = new ArrayList<String>();
		for (Route rts : camelContext.getRoutes()) {
			retList.add("Id: " + rts.getId() + " Endpoint: " + rts.getEndpoint());
		}
		return retList;
	}

	public void updateRoutes(Routes routes) {
		for (Route rts : camelContext.getRoutes()) {
			System.out.println(rts.getId() + " " + rts.getEndpoint());
		}
	}
}

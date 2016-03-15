package com.uline.order.processing.dynamic;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "routes")
public class Routes {
	private String routeXml;

	public Routes() {
	}

	public String getRouteXml() {
		return routeXml;
	}

	public void setRouteXml(String routeXml) {
		this.routeXml = routeXml;
	}

}

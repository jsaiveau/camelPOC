package com.uline.order.processing.orderprocessor;

import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "orderprocessor")
public class OrderProcessor {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

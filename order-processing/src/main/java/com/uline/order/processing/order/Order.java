package com.uline.order.processing.order;


import javax.xml.bind.annotation.XmlRootElement;




@XmlRootElement(name = "order")
public class Order {
	private String message;

	public Order() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
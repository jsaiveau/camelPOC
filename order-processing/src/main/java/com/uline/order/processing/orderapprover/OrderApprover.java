package com.uline.order.processing.orderapprover;

import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "orderapprover")
public class OrderApprover {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

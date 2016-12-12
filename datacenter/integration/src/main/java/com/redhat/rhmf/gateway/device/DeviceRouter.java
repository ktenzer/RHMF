package com.redhat.rhmf.gateway.device;

import org.apache.camel.Exchange;
import org.apache.camel.PropertyInject;

public class DeviceRouter {

	@PropertyInject("broker.host")
	String host;

	@PropertyInject("broker.userName")
	String userName;

	@PropertyInject("broker.password")
	String password;

	public String routeTo(Exchange exchange) {
		String deviceTopic = "mqtt://datacenter?host=" + host + "&userName=" + userName + "&password=" + password
				+ "&publishTopicName=rhmfd/" + exchange.getIn().getHeader("device", String.class);

		return deviceTopic;
	}
}

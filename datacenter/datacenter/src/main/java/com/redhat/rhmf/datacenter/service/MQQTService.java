package com.redhat.rhmf.datacenter.service;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MQQTService implements MqttCallback {
	MqttClient client;

	public MQQTService() {
		
	}
	

	public MqttClient getConnection(String server) {
	    try {
	        client = new MqttClient(server, "Sending");
	        client.connect();
	        client.setCallback(this);
	    } catch (MqttException e) {
	        e.printStackTrace();
	    }
		return client;
	}
	
	public void sendMessage(MqttClient client, String topic, String message) {
		try {
			client.subscribe(topic);
			MqttMessage messageHandle = new MqttMessage();
			messageHandle.setPayload(message.getBytes());
			client.publish(topic, messageHandle);
	    } catch (MqttException e) {
	        e.printStackTrace();
	    }
	}
	
	public void disconnect(MqttClient client) {
		try {
			client.disconnect();
	    } catch (MqttException e) {
	        e.printStackTrace();
	    }	
	}

	@Override
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}
}

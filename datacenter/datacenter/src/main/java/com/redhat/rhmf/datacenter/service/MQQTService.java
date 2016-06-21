package com.redhat.rhmf.datacenter.service;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.logging.Logger;

import javax.inject.Inject;

public class MQQTService implements MqttCallback {
    @Inject
    private Logger log;
    
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
	
	public void parseMessages(MqttClient client, String topic) {
		try {			

            client.setCallback(this);
            client.subscribe("rhmfd/8635183");
            
            
			//byte[] payload = messageHandle.getPayload();
			//log.info("test123");
			/*
			try {
				String msg = new String(payload, "UTF-8");
				log.info("test123");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 */
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
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		// TODO Auto-generated method stub
	    String time = new Timestamp(System.currentTimeMillis()).toString();
	    System.out.println("Time:\t" +time +
	        "  Topic:\t" + topic + 
	        "  Message:\t" + new String(message.getPayload()) +
	        "  QoS:\t" + message.getQos());
		log.info("test" + topic);
	}
}

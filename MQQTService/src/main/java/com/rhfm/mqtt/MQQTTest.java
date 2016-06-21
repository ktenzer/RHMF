package com.rhfm.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MQQTTest implements MqttCallback {

MqttClient client;

public MQQTTest() {
}

public static void main(String[] args) {
    new MQQTTest().lightOn();
    
    try {
        Thread.sleep(10000);                 //1000 milliseconds is one second.
    } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
    }
    
    new MQQTTest().lightOff();
}

public void lightOn() {
    
	String server = "tcp://rhmfgateway:1883";
	String topic = "rhmfd/8635183";
	String message = "LED_TOP_GREEN=ON" + "\n" + "LED_TOP_BLUE=ON";
    try {
        client = new MqttClient(server, "Sending");
        client.connect();
        client.setCallback(this);
        client.subscribe(topic);
        MqttMessage messageHandle = new MqttMessage();
        messageHandle.setPayload(message.getBytes());
        client.publish(topic, messageHandle);
        client.disconnect();
    } catch (MqttException e) {
        e.printStackTrace();
    }
}

public void lightOff() {
    
	String server = "tcp://rhmfgateway:1883";
	String topic = "rhmfd/8635183";
	String message = "LED_TOP_GREEN=OFF" + "\n" + "LED_TOP_BLUE=OFF";
    try {
        client = new MqttClient(server, "Sending");
        client.connect();
        client.setCallback(this);
        client.subscribe(topic);
        MqttMessage messageHandle = new MqttMessage();
        messageHandle.setPayload(message.getBytes());
        client.publish(topic, messageHandle);
        client.disconnect();
    } catch (MqttException e) {
        e.printStackTrace();
    }
}

public void connectionLost(Throwable cause) {
    // TODO Auto-generated method stub

}

public void deliveryComplete(IMqttDeliveryToken arg0) {
	// TODO Auto-generated method stub
	
}

public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
	// TODO Auto-generated method stub
	
}
}

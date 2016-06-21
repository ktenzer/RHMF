/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.rhmf.datacenter.service;

import com.redhat.rhmf.datacenter.model.Member;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.eclipse.paho.client.mqttv3.MqttClient;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class MemberRegistration {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Member> memberEventSrc;
    
    public static MQQTService mqqt = null;
    public static MqttClient client = null;

    
    public void register(Member member) throws Exception {
    	
        log.info("Registering " + member.getGateway());
        
        em.persist(member);
        memberEventSrc.fire(member);
        
        if (mqqt == null) {
        	log.info("creating connection");
        	mqqt = new MQQTService();
        	String server = "tcp://rhmfgateway:1883";
        	client = mqqt.getConnection(member.getGateway());
        }

        client.subscribe(member.getDevice());
    	//String server = "tcp://rhmfgateway:1883";
    	//String topic = "rhmfd/8635183";
    	//String topic = "nodemcu/13574211";
    	//String message = "LED_TOP_GREEN=ON" + "\n" + "LED_TOP_BLUE=ON";
    	//String topic = "rhmfd/ping";
    	//client.subscribe(topic);
    	//mqqt.parseMessages(client, topic);
        //log.info("here " + client.getServerURI()); 
        // mqqt.parseMessages(client, topic);
        //mqqt.sendMessage(client, member.getDevice(), message);
        //mqqt.disconnect(client);
    }
    
} 
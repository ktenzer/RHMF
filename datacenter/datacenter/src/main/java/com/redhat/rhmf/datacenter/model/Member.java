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
package com.redhat.rhmf.datacenter.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@Table(name = "Member_rhmf_")
public class Member implements Serializable {
    /** Default value included to remove warning. Remove or modify at will. **/
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    //@Size(min = 1, max = 25, message = "1-25 letters and spaces")
    //@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    private String gateway;

    @NotNull
    //@NotEmpty
    //@Email(message = "Invalid format")
    private String device;

    //@NotNull
    //@Size(min = 10, max = 12, message = "10-12 Numbers")
    //@Digits(fraction = 0, integer = 12, message = "Not valid")
    //@Column(name = "phone_number")
    //private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String name) {
        this.gateway = name;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String email) {
        this.device = email;
    }

    /*
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    */
}

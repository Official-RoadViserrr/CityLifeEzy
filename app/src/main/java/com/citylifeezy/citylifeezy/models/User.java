package com.citylifeezy.citylifeezy.models;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

/**
 * Created by Tomas on 7/26/2015.
 */
public class User {
    private String id;
    private String phone;
    private String validity;
    private int balance;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DateTime getValidityDate(){
        return ISODateTimeFormat.dateTime().parseDateTime(validity);

    }
}

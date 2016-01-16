package com.citylifeezy.citylifeezy;

/**
 * Created by Asif on 12/19/2015.
 */
public class Request_contact {
    private String name;
    private String phone;

    public Request_contact(String name, String phone) {
        super();
        this.name = name;
        this.phone = phone;
    }

    /*public Request_contact(String name) {
        super();
        this.name = name;
        this.phone = phone;
    }*/

    public String getName(){
        return name;
    }

    public String getPhone(){
        return phone;
    }
}

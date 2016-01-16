package com.citylifeezy.citylifeezy;

/**
 * Created by Asif on 12/5/2015.
 */
public class Contact_custom{
    private String name;
    private String phone;

    public Contact_custom(String name, String phone) {
        super();
        this.name = name;
        this.phone = phone;
    }

    public String getName(){
        return name;
    }

    public String getPhone(){
        return phone;
    }
}

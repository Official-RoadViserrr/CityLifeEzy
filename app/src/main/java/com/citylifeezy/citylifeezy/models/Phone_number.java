package com.citylifeezy.citylifeezy.models;

/**
 * Created by Asif on 8/25/2015.
 */
public class Phone_number {

    private String name;
    private String phone1;
    private String phone2;

    public Phone_number(String name, String phone1, String phone2) {
        super();
        this.name = name;
        this.phone1 = phone1;
        this.phone2 = phone2;
    }

    public String getName(){
        return name;
    }

    public String getPhone1(){
        return phone1;
    }

    public String getPhone2(){
        return phone2;
    }
}

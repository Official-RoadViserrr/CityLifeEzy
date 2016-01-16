package com.citylifeezy.citylifeezy.models;

/**
 * Created by Rupom on 11/18/2015.
 */
public class PhoneNumber {
    private String name;
    private String phone1;
    private String phone2;

    public PhoneNumber(String name, String phone1, String phone2) {
        super();
        this.name = name;
        this.phone1 = phone1;
        this.phone2 = phone2;
    }

    public PhoneNumber() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }
}

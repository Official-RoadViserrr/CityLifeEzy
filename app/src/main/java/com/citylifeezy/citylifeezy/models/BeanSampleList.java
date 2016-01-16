package com.citylifeezy.citylifeezy.models;

/**
 * Created by nirav kalola on 10/30/2014.
 */
public class BeanSampleList {

    int id;
    String area_name;


    public BeanSampleList() {
        super();
    }

    public BeanSampleList(int id, String area_name)
    {
        super();

        this.id = id;
        this.area_name = area_name;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea_Name() {
        return area_name;
    }

    public void setArea_Name(String area_name) { this.area_name = area_name; }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BeanSampleList other = (BeanSampleList) obj;
        if (id != other.id)
            return false;
        return true;
    }
}

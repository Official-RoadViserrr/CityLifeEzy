package com.citylifeezy.citylifeezy.models;

/**
 * Created by Asif on 10/5/2015.
 */
public class RV_CngData {

    private String Area_Id;
    private String Jam_stat;
    private String area_Id;
    private String jam_Stat;

    public RV_CngData(){

    }

    public RV_CngData(String area_Id,String jam_stat){
        super();
        this.setArea_Id(area_Id);
        this.setJam_Stat(jam_stat);
    }

    public void setArea_Id(String area_Id) {
        this.area_Id = area_Id;
    }

    public void setJam_Stat(String jam_Stat) {
        this.jam_Stat = jam_Stat;
    }

    public String getArea_Id(){
        return Area_Id;
    }

    public String getJam_stat(){
        return jam_Stat;
    }
}

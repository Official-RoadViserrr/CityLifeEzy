package com.citylifeezy.citylifeezy.models;

/**
 * Created by tanvirsourov on 5/28/15.
 */
public class RV_RoadData {


    private String Id;
    private int Area_Id;
    private String Jam_Stat_1;
    private String Jam_Stat_2;
    private String Jam_Stat_3;
    private String Jam_Stat_4;
    private String Update_Time;
    private boolean Is_Active;

    public RV_RoadData() {

    }

    public RV_RoadData(String id, int area_id,
                       String jam_stat_1, String jam_stat_2, String jam_stat_3, String jam_stat_4,
                       String update_time, boolean is_active)
    {
        this.setId(id);
        this.setArea_Id(area_id);
        this.setJam_Stat_1(jam_stat_1);
        this.setJam_Stat_2(jam_stat_2);
        this.setJam_Stat_3(jam_stat_3);
        this.setJam_Stat_4(jam_stat_4);
        this.setUpdate_Time(update_time);
        this.setIs_Active(is_active);
    }



    public String getId() {
        return Id;
    }

    public int getArea_Id() { return Area_Id; }


    public String getJam_Stat_1() { return Jam_Stat_1; }

    public String getJam_Stat_2() { return Jam_Stat_2; }

    public String getJam_Stat_3() { return Jam_Stat_3; }

    public String getJam_Stat_4() { return Jam_Stat_4; }


    public String getUpdate_Time() { return Update_Time; }

    public boolean getIs_Active() { return Is_Active; }


    @Override
    public boolean equals(Object o) {
        return o instanceof RV_RoadData && ((RV_RoadData) o).Id == Id;
    }


    public final void setId(String id) {
        Id = id;
    }


    public void setArea_Id(int area_Id) {
        Area_Id = area_Id;
    }


    public void setJam_Stat_1(String jam_Stat_1) {
        Jam_Stat_1 = jam_Stat_1;
    }

    public void setJam_Stat_2(String jam_Stat_2) {
        Jam_Stat_2 = jam_Stat_2;
    }

    public void setJam_Stat_3(String jam_Stat_3) { Jam_Stat_3 = jam_Stat_3; }

    public void setJam_Stat_4(String jam_Stat_4) {
        Jam_Stat_4 = jam_Stat_4;
    }


    public void setUpdate_Time(String update_Time) {
        Update_Time = update_Time;
    }

    public void setIs_Active(boolean is_Active) {
        Is_Active = is_Active;
    }


}

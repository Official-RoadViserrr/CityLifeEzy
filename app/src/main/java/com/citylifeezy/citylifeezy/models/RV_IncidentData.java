package com.citylifeezy.citylifeezy.models;

/**
 * Created by tanvirsourov on 5/28/15.
 */
public class RV_IncidentData {


    private String Id;
    private String Incident_Name;
    private String Central_Cord;
    private String Update_Time;
    private boolean Is_Active;


    public RV_IncidentData(String id, String incident_name, String update_time, String is_active) {

    }

    public RV_IncidentData(String id, String incident_name, String central_cord, String update_time, boolean is_active)
    {
        this.setId(id);
        this.setIncident_Name(incident_name);
        this.setCentral_Cord(central_cord);
        this.setUpdate_Time(update_time);
        this.setIs_Active(is_active);
    }


    public String getId() {
        return Id;
    }

    public String getIncident_Name() { return Incident_Name; }

    public String getCentral_Cord() { return Central_Cord; }

    public String getUpdate_Time() { return Update_Time; }

    public boolean getIs_Active() { return Is_Active; }


    @Override
    public boolean equals(Object o) {
        return o instanceof RV_IncidentData && ((RV_IncidentData) o).Id == Id;
    }


    public final void setId(String id) {
        Id = id;
    }


    public void setIncident_Name(String incident_Name) {
        Incident_Name = incident_Name;
    }

    public void setCentral_Cord(String central_Cord) {
        Central_Cord = central_Cord;
    }


    public void setUpdate_Time(String update_Time) {
        Update_Time = update_Time;
    }

    public void setIs_Active(boolean is_Active) {
        Is_Active = is_Active;
    }

}

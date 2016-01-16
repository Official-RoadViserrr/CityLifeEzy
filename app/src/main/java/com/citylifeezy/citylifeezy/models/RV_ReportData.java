package com.citylifeezy.citylifeezy.models;

/**
 * Created by tanvirsourov on 5/28/15.
 */
public class RV_ReportData {

    private String id;
    private String traffic_From;
    private String traffic_To;
    private String traffic_Condition;
    private String remarks;
    private String report_Time;

    public RV_ReportData() {

    }

    public RV_ReportData(String id,
                         String traffic_from, String traffic_to, String traffic_condition, String remarks,
                         String report_time)
    {
        this.setId(id);
        this.setTraffic_From(traffic_from);
        this.setTraffic_To(traffic_to);
        this.setTraffic_Condition(traffic_condition);
        this.setRemarks(remarks);
        this.setReport_Time(report_time);
    }



    public String getId() {
        return id;
    }


    public String getTraffic_From() { return traffic_From; }

    public String getTraffic_To() { return traffic_To; }

    public String getTraffic_Condition() { return traffic_Condition; }

    public String getRemarks() { return remarks; }

    public String getReport_Time() { return report_Time; }



    @Override
    public boolean equals(Object o) {
        return o instanceof RV_ReportData && ((RV_ReportData) o).id == id;
    }


    public final void setId(String id) {
        id = id;
    }


    public void setTraffic_From(String traffic_From) {
        traffic_From = traffic_From;
    }

    public void setTraffic_To(String traffic_To) {
        traffic_To = traffic_To;
    }

    public void setTraffic_Condition(String traffic_Condition) { traffic_Condition = traffic_Condition; }

    public void setRemarks(String remarks) {
        remarks = remarks;
    }

    public void setReport_Time(String report_Time) {
        report_Time = report_Time;
    }

}

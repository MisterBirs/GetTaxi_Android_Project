package com.example.shlomi.gettaxi_android_project.model.entities;

import java.util.Date;

public class Travel {

    //region Fields
    protected TravelStatus status;
    protected String sourceLocation;
    protected String destinationLocation;
    protected Date startTime;
    protected Date endTime;



    protected String customerName;
    protected String customerPhone;
    protected String customerEmail;
    //endregion
    //region Constructors
    public Travel(TravelStatus status, String sourceLocation, String destinationLocation, Date startTime, Date endTime, String customerName, String customerPhone, String customerEmail) {
        this.status = status;
        this.sourceLocation = sourceLocation;
        this.destinationLocation = destinationLocation;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
    }
    public Travel(){}
    //endregion
    //region Seters and Geters

    public TravelStatus getStatus() {
        return status;
    }

    public void setStatus(TravelStatus status) {
        this.status = status;
    }

    public String getSourceLocation() {
        return sourceLocation;
    }

    public void setSourceLocation(String sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    //endregion
}

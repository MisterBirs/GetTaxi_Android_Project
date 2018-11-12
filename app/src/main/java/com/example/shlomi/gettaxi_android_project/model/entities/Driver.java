package com.example.shlomi.gettaxi_android_project.model.entities;

public class Driver {
    //region Fields
    protected int idNumber;
     protected String firstName;
     protected String lastName;
     protected int phoneNumber;
     protected String emailAdress;
    protected int creditNumber;
    //endregion
    //region Constrctor
    public Driver(int idNumber, String firstName, String lastName, int phoneNumber, String emailAdress, int creditNumber) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAdress = emailAdress;
        this.creditNumber = creditNumber;
    }
    //endregion
    //region Set and Gets
    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public int getCreditNumber() {
        return creditNumber;
    }

    public void setCreditNumber(int creditNumber) {
        this.creditNumber = creditNumber;
    }
    //endregion
}

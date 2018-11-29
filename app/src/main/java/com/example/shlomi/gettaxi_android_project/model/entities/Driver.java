package com.example.shlomi.gettaxi_android_project.model.entities;

public class Driver {
    //region Fields
    protected int idNumber;
     protected String name;
    protected String password;
     protected int phoneNumber;
     protected String emailAdress;
    protected int creditNumber;

    //endregion
    //region Constrctor
    public Driver(){};
    public Driver(int idNumber, String name,int phoneNumber, String emailAdress, int creditNumber, String password) {
        this.idNumber = idNumber;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAdress = emailAdress;
        this.creditNumber = creditNumber;
        this.password = password;
    }
    //endregion
    //region Set and Gets
    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String firstName) {
        this.name = firstName;
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

package com.example.shlomi.gettaxi_android_project.model.datasource;

import com.example.shlomi.gettaxi_android_project.model.entities.Driver;
import com.example.shlomi.gettaxi_android_project.model.entities.Travel;

public interface IDataBase {
    //region Inner InterFaces
    public interface Action{
        void onSuccess();
        void onFailure(Exception exception);
        void onProgress(String status, double percent);
    }

    public interface NotifyDataChange<T> {
        void OnDataChanged(T obj);
        void onFailure(Exception exception);
    }
    //endregion
    //region Methods
    void addTravel(Travel travelToAdd, Action action);
    void addDriver(Driver driverToAdd, Action action);
    void isValidDriverAuthentication(String emailForCheck, String passwordForCheck, Action action);
    //endregion
}

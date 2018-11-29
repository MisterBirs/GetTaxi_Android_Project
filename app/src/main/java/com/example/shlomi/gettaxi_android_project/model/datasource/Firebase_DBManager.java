package com.example.shlomi.gettaxi_android_project.model.datasource;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.shlomi.gettaxi_android_project.controller.MainActivity;
import com.example.shlomi.gettaxi_android_project.model.entities.Driver;
import com.example.shlomi.gettaxi_android_project.model.entities.Travel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Firebase_DBManager implements IDataBase{
    //region Fields
    static DatabaseReference travelsRef;
    static DatabaseReference driverRef;
    static List<Travel> travelsList;
    //endregion
    //region initialization
    static {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        travelsRef = database.getReference("Travels");
        driverRef = database.getReference("Drivers");
        travelsList = new ArrayList<>();
    }
    //endregion
    //region Methods
    @Override
    public void addTravel(Travel travelToAdd, final Action<Void> action) {
        Task<Void> task = travelsRef.push().setValue(travelToAdd);

        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                action.onSuccess(aVoid);
            }
        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                action.onFailure(e);
            }
        });
    }

    @Override
    public void addDriver(Driver driverToAdd, final Action<Void> action) {
        Task<Void> task = driverRef.push().setValue(driverToAdd);
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                action.onSuccess(aVoid);
            }
        });
    }
    //endregion
}



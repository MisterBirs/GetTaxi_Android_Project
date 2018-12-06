package com.example.shlomi.gettaxi_android_project.model.datasource;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.shlomi.gettaxi_android_project.controller.MainActivity;
import com.example.shlomi.gettaxi_android_project.model.entities.Driver;
import com.example.shlomi.gettaxi_android_project.model.entities.Travel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
    public void addTravel(Travel travelToAdd, final Action action) {
        Task<Void> task = travelsRef.push().setValue(travelToAdd);

        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                action.onSuccess();
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
    public void addDriver(Driver driverToAdd, final Action action) {
        Task<Void> task = driverRef.push().setValue(driverToAdd);
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                action.onSuccess();
            }
        });
    }

    @Override
    public void isValidDriverAuthentication(String emailForCheck, final String passwordForCheck, final Action action) {
        Query query  = driverRef.orderByChild("emailAdress").equalTo(emailForCheck);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Driver checkDriver=dataSnapshot.getChildren().iterator().next().getValue(Driver.class);
                    if(checkDriver.getPassword().equals(passwordForCheck))
                        action.onSuccess();
                    else
                        action.onFailure(new Exception("הסיסמא שהוזנה שגויה"));
                }
                else
                    action.onFailure(new Exception("המשתמש לא קיים"));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    //endregion
}



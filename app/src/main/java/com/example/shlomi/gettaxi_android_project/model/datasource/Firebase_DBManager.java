package com.example.shlomi.gettaxi_android_project.model.datasource;

import android.support.annotation.NonNull;

import com.example.shlomi.gettaxi_android_project.model.entities.Travel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Firebase_DBManager {
    public interface Action<T> {
        void onSuccess(T obj);
        void onFailure(Exception exception);
        void onProgress(String status, double percent);
    }

        public interface NotifyDataChange<T> {
        void OnDataChanged(T obj);
        void onFailure(Exception exception);
    }

    static DatabaseReference travelsRef;
    static List<Travel> travelsList;
    static {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        travelsRef = database.getReference("Travels");
        travelsList = new ArrayList<>();
    }

    public static void addTravelToFirebase(final Travel travel, final Action<Long> action){
        travelsRef.child(String.valueOf(travel.getId())).setValue(travel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                action.onSuccess((long) travel.getId());
                action.onProgress("upload travel data",100);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                action.onFailure(e);
                action.onProgress("Error upload travel data",100);
            }
        });
    }
    }



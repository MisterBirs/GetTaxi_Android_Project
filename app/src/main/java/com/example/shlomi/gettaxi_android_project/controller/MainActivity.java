package com.example.shlomi.gettaxi_android_project.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shlomi.gettaxi_android_project.model.datasource.Firebase_DBManager;
import com.example.shlomi.gettaxi_android_project.model.entities.Travel;
import com.example.shlomi.gettaxi_android_project.model.entities.TravelStatus;
import com.google.firebase.database.*;

import com.example.shlomi.gettaxi_android_project.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public Button inviteTaxiBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inviteTaxiBtn = (Button) findViewById(R.id.inviteTaxiBtn);



     /*   FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Classes");
        DatabaseReference databaseReference1 = databaseReference.child("ClassA");

        HashMap<String,Object> myHashMap = new HashMap<String,Object>();
        myHashMap.put("1111","PersonA");
        myHashMap.put("1112",null);
        myHashMap.put("1113","PersonC");
        databaseReference1.updateChildren(myHashMap);*/



        /*databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s = dataSnapshot.getValue(String.class);
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();

            }
        });*/




    }

    protected Travel createTravelInstnceFromFields()
    {
        EditText phoneNumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
        EditText emailEditText = (EditText) findViewById(R.id.emailEditText);
        EditText destinationEditText = (EditText) findViewById(R.id.destinationEditText);

        Travel travel = new Travel(TravelStatus.READY, "Ramle", destinationEditText.getText().toString(), new Date(), new Date(), "Shlomi", phoneNumberEditText.getText().toString(), emailEditText.getText().toString());
        return travel;
    }

    protected void inviteTaxiBtnOnClick(View view)
    {
        Button inviteTaxiBtn = findViewById(R.id.inviteTaxiBtn);
        if(view == inviteTaxiBtn)
        {
            Travel travel = createTravelInstnceFromFields();
            addTravel(travel);
        }
    }

    private void addTravel(Travel travel)
    {
        try {
            inviteTaxiBtn.setEnabled(false);
            Firebase_DBManager.addTravelToFirebase(travel, new Firebase_DBManager.Action<Long>() {
                @Override
                public void onSuccess(Long obj) {
                    Toast.makeText(getBaseContext(), "insert id " + obj, Toast.LENGTH_LONG).show();
                    inviteTaxiBtn.setEnabled(true);
                }

                @Override
                public void onFailure(Exception exception) {
                    Toast.makeText(getBaseContext(), "Error \n" + exception.getMessage(), Toast.LENGTH_LONG).show();
                    inviteTaxiBtn.setEnabled(true);
                }

                @Override
                public void onProgress(String status, double percent) {
                    if( percent != 100)
                        inviteTaxiBtn.setEnabled(false);
                }
            });
        } catch (Exception e){
            Toast.makeText(getBaseContext(), "Error \n", Toast.LENGTH_LONG).show();
            inviteTaxiBtn.setEnabled(true);
        }


    }
}

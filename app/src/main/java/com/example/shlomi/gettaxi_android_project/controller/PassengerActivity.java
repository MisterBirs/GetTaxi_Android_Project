package com.example.shlomi.gettaxi_android_project.controller;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shlomi.gettaxi_android_project.R;
import com.example.shlomi.gettaxi_android_project.model.datasource.FactoryDataBase;
import com.example.shlomi.gettaxi_android_project.model.datasource.IDataBase;
import com.example.shlomi.gettaxi_android_project.model.entities.Travel;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class PassengerActivity extends AppCompatActivity {

    //region Fields
    public Button inviteTaxiBtn;
    protected ActivityDataBase2 activityDataBase;
    LocationManager locationManager;
    protected LocationListener locationListener;
    EditText nameEditText;
    EditText phoneNumberEditText;
    EditText emailEditText;
    EditText currentLocationEditText;
    EditText destinationEditText;
    final int PLACE_PICKER_REQUEST = 1;

    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);
        initializeFields();
        getLocation();
    }
    protected void initializeFields() {
        inviteTaxiBtn = (Button) findViewById(R.id.inviteTaxiBtn);
        nameEditText = (EditText) findViewById(R.id.NameEditText);
        phoneNumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        currentLocationEditText = (EditText) findViewById(R.id.currentLocationEditText);
        destinationEditText  = (EditText) findViewById(R.id.destinationEditText);

        activityDataBase = new ActivityDataBase2();
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                currentLocationEditText.setText(getPlace(location));
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
    }
    protected Travel createTravelInstnceFromFields() {
        Travel travel = new Travel();
        travel.setCustomerName(nameEditText.getText().toString());
        travel.setCustomerPhone(phoneNumberEditText.getText().toString());
        travel.setCustomerEmail(emailEditText.getText().toString());
        travel.setSourceLocation(currentLocationEditText.getText().toString());
        travel.setDestinationLocation(destinationEditText.getText().toString());
        return travel;
    }
    public void inviteTaxiBtnOnClick(View view) {
        Button inviteTaxiBtn = findViewById(R.id.inviteTaxiBtn);
        if(view == inviteTaxiBtn)
        {
            Travel travel = createTravelInstnceFromFields();
            activityDataBase.addTravel(travel);
        }
    }
    protected class ActivityDataBase2{
        protected void addTravel(Travel travel)
        {
            try {
                inviteTaxiBtn.setEnabled(false);
                IDataBase dataBase = FactoryDataBase.getDataBase();
                dataBase.addTravel(travel, new IDataBase.Action<Void>() {
                    @Override
                    public void onSuccess(Void obj) {
                        Toast.makeText(getBaseContext(), "הנסיעה הוספה בהצלחה", Toast.LENGTH_LONG).show();
                        inviteTaxiBtn.setEnabled(true);
                    }

                    @Override
                    public void onFailure(Exception exception) {
                        Toast.makeText(getBaseContext(), "הוספת הנסיעה נכשלה", Toast.LENGTH_LONG).show();
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
    };

    //region Gps Methods
    private void getLocation() {

        //     Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 5);


        } else {
            // Android version is less than 6.0 or the permission is already granted.
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

    }
    public String getPlace(Location location) {

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);


            if (addresses.size() > 0) {
                String adress = addresses.get(0).getAddressLine(0);
                return adress;


            }

            return "no place: \n ("+location.getLongitude()+" , "+location.getLatitude()+")";
        }
        catch(
                IOException e)

        {
            e.printStackTrace();
        }
        return "IOException ...";
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 5) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the location", Toast.LENGTH_SHORT).show();
            }
        }

    }
    //endregion
}

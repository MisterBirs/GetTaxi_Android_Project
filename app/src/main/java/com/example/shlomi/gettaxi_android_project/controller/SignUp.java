package com.example.shlomi.gettaxi_android_project.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shlomi.gettaxi_android_project.R;
import com.example.shlomi.gettaxi_android_project.model.datasource.FactoryDataBase;
import com.example.shlomi.gettaxi_android_project.model.datasource.IDataBase;
import com.example.shlomi.gettaxi_android_project.model.entities.Driver;
import com.example.shlomi.gettaxi_android_project.model.entities.Travel;

public class SignUp extends AppCompatActivity {
    Button signUpBtn;
    EditText nameField;
    EditText phoneNumberField;
    EditText emailField;
    EditText idNumberField;
    EditText creditNumberField;
    EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initializeFields();
    }
    protected void initializeFields() {
        signUpBtn = (Button) findViewById(R.id.signUpBtnOfRegistration);
        nameField = (EditText)findViewById(R.id.nameEditText);
        idNumberField =(EditText)  findViewById(R.id.idNumberEditText);
        phoneNumberField = (EditText) findViewById(R.id.phoneNumberEditText);
        emailField = (EditText) findViewById(R.id.emailEditText);
        creditNumberField = (EditText) findViewById(R.id.creditNumberEditText);
        passwordField = (EditText) findViewById(R.id.passwordEditText);


    }
    protected Driver createDriverInstnceFromFields() {
      Driver driver = new Driver();

      driver.setIdNumber(Integer.parseInt(idNumberField.getText().toString()));
      driver.setName(nameField.getText().toString());
      driver.setPhoneNumber(Integer.parseInt(phoneNumberField.getText().toString()));
      driver.setEmailAdress(emailField.getText().toString());
      driver.setCreditNumber(Integer.parseInt(creditNumberField.getText().toString()));
      driver.setPassword(passwordField.getText().toString());

      return  driver;
    }
    public void signUpOnClick(View view){
        Driver driver = createDriverInstnceFromFields();
        ActivitySignUpDataBase activitySignUpDataBase = new ActivitySignUpDataBase();
        activitySignUpDataBase.addDriver(driver);
    }
    protected  class ActivitySignUpDataBase{
        protected  void addDriver(Driver driver) {
            try {
                signUpBtn.setEnabled(false);
                IDataBase dataBase = FactoryDataBase.getDataBase();
                dataBase.addDriver(driver, new IDataBase.Action<Void>() {
                    @Override
                    public void onSuccess(Void obj) {
                        Toast.makeText(getBaseContext(), "ההרשמה בוצעה בהצלחה", Toast.LENGTH_LONG).show();
                        signUpBtn.setEnabled(true);
                    }

                    @Override
                    public void onFailure(Exception exception) {
                        Toast.makeText(getBaseContext(), "ההרשמה נכשלה", Toast.LENGTH_LONG).show();
                        signUpBtn.setEnabled(true);
                    }

                    @Override
                    public void onProgress(String status, double percent) {
                        if( percent != 100)
                            signUpBtn.setEnabled(false);
                    }
                });
            } catch (Exception e){
                Toast.makeText(getBaseContext(), "Error \n", Toast.LENGTH_LONG).show();
                signUpBtn.setEnabled(true);
            }


        }
    };



}

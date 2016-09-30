/*
 * Created By Kulomady on 9/30/16 3:33 PM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/30/16 3:33 PM
 */

package com.arm.hackbri.landmoney.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.arm.hackbri.landmoney.R;
import com.arm.hackbri.landmoney.view.RegisterView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements RegisterView{

    @Bind(R.id.full_name)
    EditText edtFullName;
    @Bind(R.id.email_address)
    EditText emailEdt;
    @Bind(R.id.password)
    EditText edtPassword;
    @Bind(R.id.password_conf)
    EditText edtPassConf;
    @Bind(R.id.phoneEdittext)
    EditText edtPhone;
    @Bind(R.id.ktp_edittext)
    EditText edtKtp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @Override
    public void renderErrorConnection(String messageError) {
        showMessage(messageError);
    }



    @Override
    public void renderErrorUnknown(String messageError) {
        showMessage(messageError);
    }

    @Override
    public String getPhoneNumber() {
        return edtPhone.getText().toString();
    }

    @Override
    public String getPassword() {
        return edtPassword.getText().toString();
    }

    @Override
    public String getPasswordConf() {
        return edtPassConf.getText().toString();
    }

    @Override
    public String getFullName() {
        return edtFullName.getText().toString();
    }

    @Override
    public String getKtp() {
        return edtKtp.getText().toString();
    }

    @Override
    public String getEmailAddress() {
        return emailEdt.getText().toString();
    }

    private void showMessage(String messageError) {
        Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
    }
}

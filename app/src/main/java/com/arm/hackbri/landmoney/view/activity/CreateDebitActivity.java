/*
 * Created By Kulomady on 9/30/16 11:14 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/30/16 11:14 AM
 */

package com.arm.hackbri.landmoney.view.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arm.hackbri.landmoney.R;
import com.arm.hackbri.landmoney.Utils;
import com.arm.hackbri.landmoney.model.response.Profile;
import com.arm.hackbri.landmoney.presenter.CreateDebitPresenter;
import com.arm.hackbri.landmoney.presenter.CreateDebitPresenterImpl;
import com.arm.hackbri.landmoney.view.CreateDebitView;

import butterknife.Bind;
import butterknife.OnClick;

public class CreateDebitActivity extends BaseActivity implements CreateDebitView{

    private static final String PROFILE_KEY = "keyProfileDebit";
    @Bind(R.id.task_create_debit)
    TextView taskCreateDebit;
    @Bind(R.id.input_amount)
    EditText edtAmount;
    @Bind(R.id.input_phonenumber)
    EditText edtPhoneNumber;

    private String phoneValue;
    private Profile profileTarget;

    private CreateDebitPresenter debitPresenter;
    private ProgressDialog progressDialog;

    public static void openActivity(Activity openingActivity, Profile profileTarget) {
        Intent intent = new Intent(openingActivity, CreateDebitActivity.class);
        intent.putExtra(PROFILE_KEY, profileTarget);
        openingActivity.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_debit);
//        ButterKnife.bind(this);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_grey600_24dp);
        updateStatusBarColor();
        profileTarget = getIntent().getParcelableExtra(PROFILE_KEY);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        debitPresenter = new CreateDebitPresenterImpl(this);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void updateStatusBarColor() {
        if (Utils.isAndroid5()) {
            getWindow().setStatusBarColor(0xff888888);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_publish, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_publish) {
            bringMainActivityToTop();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void bringMainActivityToTop() {
        Intent intent = new Intent(this, DebitActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setAction(CreditActivity.ACTION_SHOW_LOADING_ITEM);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

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
        return edtPhoneNumber.getText().toString();
    }

    @Override
    public int getAmount() {
        return Integer.parseInt(edtAmount.getText().toString());
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void successCreateDebit() {
        progressDialog.dismiss();
        bringMainActivityToTop();
    }


    private void showMessage(String messageError) {
        Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnSubmit)
    void btnSubmitClicked() {
        progressDialog.show();
        debitPresenter.processCreateDebit(this,profileTarget.getUserId().toString());
    }


}

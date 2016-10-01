package com.arm.hackbri.landmoney.view.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arm.hackbri.landmoney.R;
import com.arm.hackbri.landmoney.Utils;
import com.arm.hackbri.landmoney.interactor.PreferencesInteractor;
import com.arm.hackbri.landmoney.interactor.PreferencesInteractorImpl;
import com.arm.hackbri.landmoney.model.response.Profile;
import com.arm.hackbri.landmoney.presenter.CreateCreditPresenter;
import com.arm.hackbri.landmoney.presenter.CreateCreditPresenterImpl;
import com.arm.hackbri.landmoney.view.CreateCreditView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateCreditActivity extends BaseActivity implements CreateCreditView {
    @Bind(R.id.task_create_credit)
    TextView taskCreateCredit;

    @Bind(R.id.input_amount)
    EditText edtAmount;
    @Bind(R.id.input_phone)
    EditText edtPhone;
    @Bind(R.id.rg_payment_method)
    RadioGroup rgPaymentMethod;
    PreferencesInteractor preferencesInteractor;

    @Bind(R.id.input_desc)
    EditText edtDesc;

    private CreateCreditPresenter createCreditPresenter;
    private static final String PROFILE_KEY="profileKey";
    private Profile profileTarget;
    private ProgressDialog progressDialog;


    public static void openWithPhotoUri(Activity openingActivity, Profile profileTarget) {
        Intent intent = new Intent(openingActivity, CreateCreditActivity.class);
        intent.putExtra(PROFILE_KEY, profileTarget);
        openingActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_credit);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_grey600_24dp);
        updateStatusBarColor();
        profileTarget = getIntent().getParcelableExtra(PROFILE_KEY);
        ButterKnife.bind(this);
        this.createCreditPresenter = new CreateCreditPresenterImpl(this);
        preferencesInteractor = new PreferencesInteractorImpl();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void updateStatusBarColor() {
        if (Utils.isAndroid5()) {
            getWindow().setStatusBarColor(0xff888888);
        }
    }

    private void bringMainActivityToTop() {
        Intent intent = new Intent(this, CreditActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        intent.setAction(CreditActivity.ACTION_SHOW_LOADING_ITEM);
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
    public void successedCreateCredit() {
        progressDialog.dismiss();
        bringMainActivityToTop();
    }

    @Override
    public String getPhoneNumber() {
        return edtPhone.getText().toString();
    }

    @Override
    public int getAmount() {
        return Integer.parseInt(edtAmount.getText().toString());
    }

    @Override
    public String getDescription() {
        return edtDesc.getText().toString();
    }

    @OnClick(R.id.btnSubmit)
    void onSubmitClicked() {
        progressDialog.show();
        createCreditPresenter.processCreateCredit(this, profileTarget.getUserId().toString() );
    }

    private void showMessage(String messageError) {
        Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
    }


}

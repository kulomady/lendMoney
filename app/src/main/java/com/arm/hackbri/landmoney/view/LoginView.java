package com.arm.hackbri.landmoney.view;

import com.arm.hackbri.landmoney.model.response.Debit;
import com.arm.hackbri.landmoney.model.response.Profile;

import java.util.List;

/**
 * LoginView
 * Created by anggaprasetiyo on 9/30/16.
 */

public interface LoginView {

    void showProgressFetchCreditList();

    void dismissProgressFetchCreditList();

    void renderProfileData(Profile data);

    void renderErrorServerFetchData(String messageError);

    void renderErrorResponseFetchData(String messageError);

    void renderErrorConnection(String messageError);

    void renderErrorUnknown(String messageError);

    String getPhoneNumber();

    String getPassword();


}

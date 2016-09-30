package com.arm.hackbri.landmoney.presenter;

import android.app.Activity;

import com.arm.hackbri.landmoney.interactor.NetworkInteractorImpl;
import com.arm.hackbri.landmoney.interactor.OnFetchDataListener;
import com.arm.hackbri.landmoney.interactor.PreferencesInteractor;
import com.arm.hackbri.landmoney.interactor.PreferencesInteractorImpl;
import com.arm.hackbri.landmoney.model.ParamNetwork;
import com.arm.hackbri.landmoney.model.response.Profile;
import com.arm.hackbri.landmoney.network.exception.GeneralErrorException;
import com.arm.hackbri.landmoney.network.exception.HttpErrorException;
import com.arm.hackbri.landmoney.view.LoginView;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * LoginPresenterImpl
 * Created by anggaprasetiyo on 9/30/16.
 */

public class LoginPresenterImpl implements LoginPresenter {
    private final LoginView viewListener;
    private final NetworkInteractorImpl netDataInteractor;
    private final PreferencesInteractor preferencesInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.viewListener = loginView;
        this.netDataInteractor = new NetworkInteractorImpl();
        this.preferencesInteractor = new PreferencesInteractorImpl();
    }

    @Override
    public void processPostLogin(final Activity activity) {
        netDataInteractor.login(new ParamNetwork.Builder().put("user_phone", viewListener.getPhoneNumber())
                .put("user_password", viewListener.getPassword())
                .build(), new OnFetchDataListener<Profile>() {
            @Override
            public void onSuccessFetchData(Profile data) {
                viewListener.renderProfileData(data);
                preferencesInteractor.storeUserData(activity, data);
            }

            @Override
            public void onFailedFetchData(Throwable throwable) {
                if (throwable instanceof SocketTimeoutException) {
                    viewListener.renderErrorConnection("Server timeout, silahkan coba kembali");
                } else if (throwable instanceof UnknownHostException) {
                    viewListener.renderErrorConnection("Tidak ada internet, Silahkan coba kembali");
                } else if (throwable instanceof HttpErrorException) {
                    viewListener.renderErrorServerFetchData(throwable.getMessage());
                } else if (throwable instanceof GeneralErrorException) {
                    viewListener.renderErrorResponseFetchData(throwable.getMessage());
                } else {
                    viewListener.renderErrorUnknown(throwable.getMessage());
                }
            }
        });
    }
}

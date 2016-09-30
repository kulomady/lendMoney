package com.arm.hackbri.landmoney.presenter;

import android.app.Activity;

import com.arm.hackbri.landmoney.interactor.NetworkInteractorImpl;
import com.arm.hackbri.landmoney.interactor.OnFetchDataListener;
import com.arm.hackbri.landmoney.interactor.PreferencesInteractor;
import com.arm.hackbri.landmoney.interactor.PreferencesInteractorImpl;
import com.arm.hackbri.landmoney.model.ParamNetwork;
import com.arm.hackbri.landmoney.model.response.CreateDebitCredit;
import com.arm.hackbri.landmoney.network.exception.GeneralErrorException;
import com.arm.hackbri.landmoney.network.exception.HttpErrorException;
import com.arm.hackbri.landmoney.view.CreateCreditView;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * CreateCreditPresenterImpl
 * Created by anggaprasetiyo on 9/30/16.
 */

public class CreateCreditPresenterImpl implements CreateCreditPresenter {
    private final NetworkInteractorImpl netInteractor;
    private final PreferencesInteractor preferencesInteractor;
    private final CreateCreditView viewListener;

    public CreateCreditPresenterImpl(CreateCreditView createCreditView) {
        this.netInteractor = new NetworkInteractorImpl();
        this.preferencesInteractor = new PreferencesInteractorImpl();
        this.viewListener = createCreditView;
    }

    @Override
    public void processCreateCredit(Activity activity, String userIdTarget) {
        ParamNetwork.Builder builder = new ParamNetwork.Builder();
        builder.put("user_id", preferencesInteractor.getUserData(activity).getUserId() + "");
        builder.put("to_user_id", userIdTarget);
        builder.put("amt", viewListener.getAmount() + "");
        builder.put("desc", viewListener.getDescription());
        netInteractor.createNewCredit(builder.build(), new OnFetchDataListener<CreateDebitCredit>() {
            @Override
            public void onSuccessFetchData(CreateDebitCredit data) {

            }

            @Override
            public void onFailedFetchData(Throwable throwable) {
                if (throwable instanceof SocketTimeoutException) {
                    viewListener.renderErrorConnection("Server timeout, silahkan coba kembali");
                } else if (throwable instanceof UnknownHostException) {
                    viewListener.renderErrorConnection("Tidak ada internet, Silahkan coba kembali");
                } else if (throwable instanceof HttpErrorException) {
                    viewListener.renderErrorUnknown(throwable.getMessage());
                } else if (throwable instanceof GeneralErrorException) {
                    viewListener.renderErrorUnknown(throwable.getMessage());
                } else {
                    viewListener.renderErrorUnknown(throwable.getMessage());
                }
            }
        });
    }
}

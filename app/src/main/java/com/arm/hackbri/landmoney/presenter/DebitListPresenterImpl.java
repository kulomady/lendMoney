package com.arm.hackbri.landmoney.presenter;

import android.app.Activity;

import com.arm.hackbri.landmoney.interactor.NetworkInteractorImpl;
import com.arm.hackbri.landmoney.interactor.OnFetchDataListener;
import com.arm.hackbri.landmoney.interactor.PreferencesInteractor;
import com.arm.hackbri.landmoney.interactor.PreferencesInteractorImpl;
import com.arm.hackbri.landmoney.model.ParamNetwork;
import com.arm.hackbri.landmoney.model.response.Debit;
import com.arm.hackbri.landmoney.network.exception.GeneralErrorException;
import com.arm.hackbri.landmoney.network.exception.HttpErrorException;
import com.arm.hackbri.landmoney.view.DebitListView;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

/**
 * DebitListPresenterImpl
 * Created by anggaprasetiyo on 9/29/16.
 */

public class DebitListPresenterImpl implements DebitListPresenter {

    private final NetworkInteractorImpl netInteractor;
    private final DebitListView viewListener;
    private final PreferencesInteractor preferencesInteractor;

    public DebitListPresenterImpl(DebitListView debitListView) {
        this.netInteractor = new NetworkInteractorImpl();
        this.preferencesInteractor = new PreferencesInteractorImpl();
        this.viewListener = debitListView;
    }

    @Override
    public void processFetchDebitList(Activity activity) {
        if (preferencesInteractor.isLogin(activity)) {
            netInteractor.getDebitList(new ParamNetwork.Builder().put("", "").build(),
                    new OnFetchDataListener<List<Debit>>() {
                        @Override
                        public void onSuccessFetchData(List<Debit> debitList) {
                            viewListener.renderDebitListDatas(debitList);
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
        } else {
            viewListener.renderUserNotLogin();
        }

    }
}

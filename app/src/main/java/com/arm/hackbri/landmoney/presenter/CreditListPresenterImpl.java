package com.arm.hackbri.landmoney.presenter;

import android.app.Activity;

import com.arm.hackbri.landmoney.interactor.NetworkInteractorImpl;
import com.arm.hackbri.landmoney.interactor.OnFetchDataListener;
import com.arm.hackbri.landmoney.model.ParamNetwork;
import com.arm.hackbri.landmoney.model.response.Debit;
import com.arm.hackbri.landmoney.network.exception.GeneralErrorException;
import com.arm.hackbri.landmoney.network.exception.HttpErrorException;
import com.arm.hackbri.landmoney.view.CreditListView;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

/**
 * CreditListPresenterImpl
 * Created by anggaprasetiyo on 9/30/16.
 */

public class CreditListPresenterImpl implements CreditListPresenter {
    private final NetworkInteractorImpl netInteractor;
    private final CreditListView viewListener;

    public CreditListPresenterImpl(CreditListView debitListView) {
        this.netInteractor = new NetworkInteractorImpl();
        this.viewListener = debitListView;
    }

    @Override
    public void processFetchCreditList(Activity activity) {
        netInteractor.getDebitList(new ParamNetwork.Builder().put("", "").build(), new OnFetchDataListener<List<Debit>>() {
            @Override
            public void onSuccessFetchData(List<Debit> debitList) {
                viewListener.renderCreditListDatas(debitList);
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

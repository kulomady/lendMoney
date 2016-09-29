package com.arm.hackbri.landmoney.presenter;

import com.arm.hackbri.landmoney.interactor.NetworkInteractorImpl;
import com.arm.hackbri.landmoney.interactor.OnFetchDataListener;
import com.arm.hackbri.landmoney.model.ParamNetwork;

/**
 * HutangListPresenterImpl
 * Created by anggaprasetiyo on 9/29/16.
 */

public class HutangListPresenterImpl implements HutangListPresenter {

    private final NetworkInteractorImpl netInteractor;

    public HutangListPresenterImpl() {
        this.netInteractor = new NetworkInteractorImpl();
    }

    @Override
    public void processFetchHutangList() {
        netInteractor.getListHutang(new ParamNetwork.Builder().put("", "").build(), new OnFetchDataListener<String>() {
            @Override
            public void onSuccessFetchData(String data) {

            }

            @Override
            public void onFailedFetchData(Throwable throwable) {

            }
        });
    }
}

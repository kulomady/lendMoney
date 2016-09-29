package com.arm.hackbri.landmoney.interactor;

import com.arm.hackbri.landmoney.model.ParamNetwork;
import com.arm.hackbri.landmoney.network.LMResponse;
import com.arm.hackbri.landmoney.network.serviceapi.LMService;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * NetworkInteractorImpl
 * Created by anggaprasetiyo on 9/29/16.
 */

public class NetworkInteractorImpl implements NetworkInteractor {
    private final CompositeSubscription compositeSubscription;

    public NetworkInteractorImpl() {
        this.compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void getListHutang(ParamNetwork paramNetwork, final OnFetchDataListener<?> onFetchDataListener) {
        compositeSubscription.add(LMService.getInstance().getApi().getHutangList(paramNetwork.getParamMap())
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LMResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LMResponse lmResponse) {

                    }
                }));

    }

    @Override
    public void getListPinjaman(ParamNetwork paramNetwork, OnFetchDataListener<?> onFetchDataListener) {
        compositeSubscription.add(LMService.getInstance().getApi().getPinjamanList(paramNetwork.getParamMap())
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LMResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LMResponse lmResponse) {

                    }
                }));

    }

    @Override
    public void getListRedeem(ParamNetwork paramNetwork, OnFetchDataListener<?> onFetchDataListener) {
        compositeSubscription.add(LMService.getInstance().getApi().getRedeemList(paramNetwork.getParamMap())
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LMResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LMResponse lmResponse) {

                    }
                }));

    }

    @Override
    public void login(ParamNetwork paramNetwork, OnFetchDataListener<?> onFetchDataListener) {
        compositeSubscription.add(LMService.getInstance().getApi().login(paramNetwork.getParamMap())
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LMResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LMResponse lmResponse) {

                    }
                }));
    }

    @Override
    public void getDetailPinjaman(ParamNetwork paramNetwork, OnFetchDataListener<?> onFetchDataListener) {
        compositeSubscription.add(LMService.getInstance().getApi().getDetailPinjaman(paramNetwork.getParamMap())
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LMResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LMResponse lmResponse) {

                    }
                }));
    }

    @Override
    public void getDetailHutang(ParamNetwork paramNetwork, OnFetchDataListener<?> onFetchDataListener) {
        compositeSubscription.add(LMService.getInstance().getApi().getDetailHutang(paramNetwork.getParamMap())
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LMResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LMResponse lmResponse) {

                    }
                }));
    }


}

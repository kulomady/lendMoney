package com.arm.hackbri.landmoney.interactor;

import com.arm.hackbri.landmoney.model.ParamNetwork;
import com.arm.hackbri.landmoney.model.response.Credit;
import com.arm.hackbri.landmoney.model.response.Debit;
import com.arm.hackbri.landmoney.model.response.Profile;
import com.arm.hackbri.landmoney.network.LMResponse;
import com.arm.hackbri.landmoney.network.serviceapi.LMService;

import java.util.List;

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
    public void getDebitList(ParamNetwork paramNetwork, final OnFetchDataListener<List<Debit>> onFetchDataListener) {
        compositeSubscription.add(LMService.getInstance().getApi().getDebitList(paramNetwork.getParamMap())
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LMResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onFetchDataListener.onFailedFetchData(e);
                    }

                    @Override
                    public void onNext(LMResponse lmResponse) {
                        onFetchDataListener.onSuccessFetchData(lmResponse.convertDataList(Debit[].class));
                    }
                }));

    }

    @Override
    public void getCreditList(ParamNetwork paramNetwork, final OnFetchDataListener<List<Credit>> onFetchDataListener) {
        compositeSubscription.add(LMService.getInstance().getApi().getCreditList(paramNetwork.getParamMap())
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LMResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onFetchDataListener.onFailedFetchData(e);
                    }

                    @Override
                    public void onNext(LMResponse lmResponse) {
                        onFetchDataListener.onSuccessFetchData(lmResponse.convertDataList(Credit[].class));
                    }
                }));

    }


    @Override
    public void login(ParamNetwork paramNetwork, final OnFetchDataListener<Profile> onFetchDataListener) {
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
                        onFetchDataListener.onFailedFetchData(e);
                    }

                    @Override
                    public void onNext(LMResponse lmResponse) {
                        onFetchDataListener.onSuccessFetchData(lmResponse.convertDataObj(Profile.class));
                    }
                }));
    }

    @Override
    public void getDebitDetail(ParamNetwork paramNetwork, OnFetchDataListener<?> onFetchDataListener) {
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
    public void getCreditDetail(ParamNetwork paramNetwork, OnFetchDataListener<?> onFetchDataListener) {
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


}

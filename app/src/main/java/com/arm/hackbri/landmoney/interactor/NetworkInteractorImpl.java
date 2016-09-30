package com.arm.hackbri.landmoney.interactor;

import com.arm.hackbri.landmoney.model.ParamNetwork;
import com.arm.hackbri.landmoney.model.response.AcceptDebit;
import com.arm.hackbri.landmoney.model.response.CreateDebitCredit;
import com.arm.hackbri.landmoney.model.response.Credit;
import com.arm.hackbri.landmoney.model.response.Debit;
import com.arm.hackbri.landmoney.model.response.Invite;
import com.arm.hackbri.landmoney.model.response.Profile;
import com.arm.hackbri.landmoney.model.response.Register;
import com.arm.hackbri.landmoney.model.response.TBankSaldo;
import com.arm.hackbri.landmoney.network.LMResponse;
import com.arm.hackbri.landmoney.network.exception.GeneralErrorException;
import com.arm.hackbri.landmoney.network.serviceapi.LMService;

import java.util.List;

import retrofit.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
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
                .map(new Func1<LMResponse, Profile>() {
                    @Override
                    public Profile call(LMResponse lmResponse) {
                        return lmResponse.convertDataList(Profile[].class).get(0);
                    }
                })
                .flatMap(new Func1<Profile, Observable<Profile>>() {
                    @Override
                    public Observable<Profile> call(Profile profile) {
                        ParamNetwork.Builder builder = new ParamNetwork.Builder();
                        builder.put("user_id", profile.getUserId() + "");
                        TBankSaldo saldo = new TBankSaldo();
                        saldo.setSaldo("100000");
                        profile.settBankSaldo(saldo);
                        //  return Observable.just(profile);
                        return Observable.zip(Observable.just(profile),
                                LMService.getInstance().getApi().getTBankSaldo(builder.build().getParamMap()),
                                new Func2<Profile, LMResponse, Profile>() {
                                    @Override
                                    public Profile call(Profile profile, LMResponse lmResponse) {
                                        TBankSaldo tBankSaldo = lmResponse.convertDataObj(TBankSaldo.class);
                                        profile.settBankSaldo(tBankSaldo);
                                        return profile;
                                    }
                                });
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Profile>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onFetchDataListener.onFailedFetchData(e);
                    }

                    @Override
                    public void onNext(Profile profile) {
                        onFetchDataListener.onSuccessFetchData(profile);
                    }
                }));
    }

    @Override
    public void acceptDebit(ParamNetwork paramNetwork, final OnFetchDataListener<AcceptDebit> onFetchDataListener) {
        compositeSubscription.add(LMService.getInstance().getApi().acceptDebit(paramNetwork.getParamMap())
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
                        AcceptDebit result = lmResponse.convertDataObj(AcceptDebit.class);
                        if (result.getSuccess() == 1) {
                            onFetchDataListener.onSuccessFetchData(result);
                        } else {
                            onFetchDataListener.onFailedFetchData(new GeneralErrorException("Gagal menerima debit"));
                        }
                    }
                }));
    }

    @Override
    public void invite(ParamNetwork paramNetwork, final OnFetchDataListener<Invite> onFetchDataListener) {
        compositeSubscription.add(LMService.getInstance().getApi().invite(paramNetwork.getParamMap())
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
                        Invite result = lmResponse.convertDataObj(Invite.class);
                        if (result.getSuccess() == 1) {
                            onFetchDataListener.onSuccessFetchData(result);
                        } else {
                            onFetchDataListener.onFailedFetchData(new GeneralErrorException("User tidak ditemukan"));
                        }
                    }
                }));
    }


    @Override
    public void register(final ParamNetwork paramNetwork, final OnFetchDataListener<Profile> onFetchDataListener) {
        compositeSubscription.add(LMService.getInstance().getApi().register(paramNetwork.getParamMap())
                .flatMap(new Func1<LMResponse, Observable<LMResponse>>() {
                    @Override
                    public Observable<LMResponse> call(LMResponse lmResponse) {
                        if (lmResponse.convertDataObj(Register.class).getSuccess() == 1)
                            return LMService.getInstance().getApi().login(paramNetwork.getParamMap());
                        else
                            throw new RuntimeException("Registrasi gagal");
                    }
                })
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
    public void createNewCredit(ParamNetwork paramNetwork, final OnFetchDataListener<CreateDebitCredit> onFetchDataListener) {
        compositeSubscription.add(LMService.getInstance().getApi().createCredit(paramNetwork.getParamMap())
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
                        CreateDebitCredit result = lmResponse.convertDataObj(CreateDebitCredit.class);
                        if (result.getSuccess() == 1) {
                            onFetchDataListener.onSuccessFetchData(lmResponse.convertDataObj(CreateDebitCredit.class));
                        } else {
                            onFetchDataListener.onFailedFetchData(new GeneralErrorException("Gagal mengajukan credit"));
                        }
                    }
                }));
    }

    @Override
    public void createNewDebit(ParamNetwork paramNetwork, final OnFetchDataListener<CreateDebitCredit> onFetchDataListener) {
        compositeSubscription.add(LMService.getInstance().getApi().createDebit(paramNetwork.getParamMap())
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
                        CreateDebitCredit result = lmResponse.convertDataObj(CreateDebitCredit.class);
                        if (result.getSuccess() == 1) {
                            onFetchDataListener.onSuccessFetchData(lmResponse.convertDataObj(CreateDebitCredit.class));
                        } else {
                            onFetchDataListener.onFailedFetchData(new GeneralErrorException("Gagal mengajukan debit"));
                        }
                    }
                }));
    }

    @Override
    public void getTBankSaldo(ParamNetwork paramNetwork, final OnFetchDataListener<TBankSaldo> onFetchDataListener) {
        compositeSubscription.add(LMService.getInstance().getApi().getTBankSaldo(paramNetwork.getParamMap())
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
                        onFetchDataListener.onSuccessFetchData(lmResponse.convertDataObj(TBankSaldo.class));
                    }
                }));
    }

    @Override
    public void transfer(final ParamNetwork paramNetwork, final OnFetchDataListener<TBankSaldo> onFetchDataListener) {
        compositeSubscription.add(LMService.getInstance().getApi().transfer(paramNetwork.getParamMap())
                .flatMap(new Func1<Response, Observable<LMResponse>>() {
                    @Override
                    public Observable<LMResponse> call(Response response) {
                        if (response.isSuccess()) {
                            return LMService.getInstance().getApi().getTBankSaldo(paramNetwork.getParamMap());
                        } else {
                            throw new RuntimeException("Transfer gagal!");
                        }
                    }
                })
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
                        onFetchDataListener.onSuccessFetchData(lmResponse.convertDataObj(TBankSaldo.class));
                    }
                }));
    }

    @Override
    public void getDialogNewDebit(ParamNetwork paramNetwork, final OnFetchDataListener<Profile> onFetchDataListener) {
        compositeSubscription.add(LMService.getInstance().getApi().getDialogNewDebit(paramNetwork.getParamMap())
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
    public void getDialogNewCredit(ParamNetwork paramNetwork, final OnFetchDataListener<Profile> onFetchDataListener) {
        compositeSubscription.add(LMService.getInstance().getApi().getDialogNewCredit(paramNetwork.getParamMap())
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
    public void postFCMToken(ParamNetwork paramNetwork) {
        compositeSubscription.add(LMService.getInstance().getApi().postFCMToken(paramNetwork.getParamMap())
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }


    //==============================================
    //  GA KEPAKEK
    //==============================================
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

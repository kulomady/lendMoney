package com.arm.hackbri.landmoney.interactor;

import com.arm.hackbri.landmoney.model.ParamNetwork;
import com.arm.hackbri.landmoney.model.response.Credit;
import com.arm.hackbri.landmoney.model.response.Debit;
import com.arm.hackbri.landmoney.model.response.Profile;
import com.arm.hackbri.landmoney.model.response.TBankSaldo;

import java.util.List;

/**
 * @author Kulomady on 9/29/16.
 */
public interface NetworkInteractor {

    void getDebitList(ParamNetwork paramNetwork, OnFetchDataListener<List<Debit>> onFetchDataListener);

    void getCreditList(ParamNetwork paramNetwork, OnFetchDataListener<List<Credit>> onFetchDataListener);

    void login(ParamNetwork paramNetwork, OnFetchDataListener<Profile> onFetchDataListener);

    void register(ParamNetwork paramNetwork, OnFetchDataListener<Profile> onFetchDataListener);

    void createNewCredit(ParamNetwork paramNetwork, OnFetchDataListener<Object> onFetchDataListener);

    void createNewDebit(ParamNetwork paramNetwork, OnFetchDataListener<Object> onFetchDataListener);

    void getTBankSaldo(ParamNetwork paramNetwork, OnFetchDataListener<TBankSaldo> onFetchDataListener);

    void transfer(ParamNetwork paramNetwork, OnFetchDataListener<TBankSaldo> onFetchDataListener);

    void getDialogNewDebit(ParamNetwork paramNetwork, OnFetchDataListener<Profile> onFetchDataListener);

    void getDialogNewCredit(ParamNetwork paramNetwork, OnFetchDataListener<Profile> onFetchDataListener);


    void getListRedeem(ParamNetwork paramNetwork, OnFetchDataListener<?> onFetchDataListener);

    void getDebitDetail(ParamNetwork paramNetwork, OnFetchDataListener<?> onFetchDataListener);

    void getCreditDetail(ParamNetwork paramNetwork, OnFetchDataListener<?> onFetchDataListener);
}

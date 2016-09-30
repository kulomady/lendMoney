package com.arm.hackbri.landmoney.interactor;

import com.arm.hackbri.landmoney.model.ParamNetwork;
import com.arm.hackbri.landmoney.model.response.AcceptDebit;
import com.arm.hackbri.landmoney.model.response.CreateDebitCredit;
import com.arm.hackbri.landmoney.model.response.Credit;
import com.arm.hackbri.landmoney.model.response.Debit;
import com.arm.hackbri.landmoney.model.response.Invite;
import com.arm.hackbri.landmoney.model.response.Profile;
import com.arm.hackbri.landmoney.model.response.TBankSaldo;
import com.arm.hackbri.landmoney.view.CreateCreditView;

import java.util.List;

/**
 * @author Kulomady on 9/29/16.
 */
public interface NetworkInteractor {

    void getDebitList(ParamNetwork paramNetwork, OnFetchDataListener<List<Debit>> onFetchDataListener);

    void getCreditList(ParamNetwork paramNetwork, OnFetchDataListener<List<Credit>> onFetchDataListener);

    void login(ParamNetwork paramNetwork, OnFetchDataListener<Profile> onFetchDataListener);

    void acceptDebit(ParamNetwork paramNetwork, OnFetchDataListener<AcceptDebit> onFetchDataListener);

    void invite(ParamNetwork paramNetwork, OnFetchDataListener<Invite> onFetchDataListener);

    void register(ParamNetwork paramNetwork, OnFetchDataListener<Profile> onFetchDataListener);

    void createNewCredit(ParamNetwork paramNetwork, OnFetchDataListener<CreateDebitCredit> onFetchDataListener);

    void createNewDebit(ParamNetwork paramNetwork, OnFetchDataListener<CreateDebitCredit> onFetchDataListener);

    void getTBankSaldo(ParamNetwork paramNetwork, OnFetchDataListener<TBankSaldo> onFetchDataListener);

    void transfer(ParamNetwork paramNetwork, OnFetchDataListener<TBankSaldo> onFetchDataListener);

    void getDialogNewDebit(ParamNetwork paramNetwork, OnFetchDataListener<Profile> onFetchDataListener);

    void getDialogNewCredit(ParamNetwork paramNetwork, OnFetchDataListener<Profile> onFetchDataListener);


    void getListRedeem(ParamNetwork paramNetwork, OnFetchDataListener<?> onFetchDataListener);

    void getDebitDetail(ParamNetwork paramNetwork, OnFetchDataListener<?> onFetchDataListener);

    void getCreditDetail(ParamNetwork paramNetwork, OnFetchDataListener<?> onFetchDataListener);
}

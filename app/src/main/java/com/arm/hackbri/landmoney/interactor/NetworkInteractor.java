package com.arm.hackbri.landmoney.interactor;

import com.arm.hackbri.landmoney.model.ParamNetwork;
import com.arm.hackbri.landmoney.model.response.Credit;
import com.arm.hackbri.landmoney.model.response.Debit;
import com.arm.hackbri.landmoney.model.response.Profile;

import java.util.List;

/**
 * @author Kulomady on 9/29/16.
 */
public interface NetworkInteractor {

    void getDebitList(ParamNetwork paramNetwork, OnFetchDataListener<List<Debit>> onFetchDataListener);

    void getCreditList(ParamNetwork paramNetwork, OnFetchDataListener<List<Credit>> onFetchDataListener);

    void getListRedeem(ParamNetwork paramNetwork, OnFetchDataListener<?> onFetchDataListener);

    void login(ParamNetwork paramNetwork, OnFetchDataListener<Profile> onFetchDataListener);

    void getDebitDetail(ParamNetwork paramNetwork, OnFetchDataListener<?> onFetchDataListener);

    void getCreditDetail(ParamNetwork paramNetwork, OnFetchDataListener<?> onFetchDataListener);
}

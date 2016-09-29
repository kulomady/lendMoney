package com.arm.hackbri.landmoney.interactor;

import com.arm.hackbri.landmoney.model.ParamNetwork;

/**
 * @author Kulomady on 9/29/16.
 */
public interface NetworkInteractor {

    void getListHutang(ParamNetwork paramNetwork, OnFetchDataListener<?> onFetchDataListener);

    void getListPinjaman(ParamNetwork paramNetwork, OnFetchDataListener<?> onFetchDataListener);

    void getListRedeem(ParamNetwork paramNetwork, OnFetchDataListener<?> onFetchDataListener);

    void login(ParamNetwork paramNetwork, OnFetchDataListener<?> onFetchDataListener);

    void getDetailPinjaman(ParamNetwork paramNetwork, OnFetchDataListener<?> onFetchDataListener);

    void getDetailHutang(ParamNetwork paramNetwork, OnFetchDataListener<?> onFetchDataListener);
}

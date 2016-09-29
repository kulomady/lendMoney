package com.arm.hackbri.landmoney.interactor;

/**
 * @author Kulomady on 9/29/16.
 */
public interface NetworkInteractor {

    void getListHutang(String userId, OnFetchDataListener onFetchDataListener);

    void getListPinjaman(String userId, OnFetchDataListener onFetchDataListener);

    void redeem(String userId, OnFetchDataListener onFetchDataListener);
}

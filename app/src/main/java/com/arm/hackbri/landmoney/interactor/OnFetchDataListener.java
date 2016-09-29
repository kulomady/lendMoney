package com.arm.hackbri.landmoney.interactor;

/**
 * @author Kulomady on 9/29/16.
 */
public interface OnFetchDataListener<T> {

    void onSuccessFetchData(T data);

    void onFailedFetchData(Throwable throwable);

}

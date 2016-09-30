package com.arm.hackbri.landmoney.view;

import com.arm.hackbri.landmoney.model.response.Debit;

import java.util.List;

/**
 * DebitListView
 * Created by anggaprasetiyo on 9/30/16.
 */

public interface DebitListView {

    void showProgressFetchDebitList();

    void dismissProgressFetchDebitList();

    void renderDebitListDatas(List<Debit> debitList);

    void renderErrorServerFetchData(String messageError);

    void renderErrorResponseFetchData(String messageError);

    void renderErrorConnection(String messageError);

    void renderErrorUnknown(String messageError);
}

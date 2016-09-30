package com.arm.hackbri.landmoney.view;

import com.arm.hackbri.landmoney.model.response.Credit;
import com.arm.hackbri.landmoney.model.response.Debit;

import java.util.List;

/**
 * CreditListView
 * Created by anggaprasetiyo on 9/30/16.
 */

public interface CreditListView {

    void showProgressFetchCreditList();

    void dismissProgressFetchCreditList();

    void renderCreditListDatas(List<Credit> debitList);

    void renderErrorServerFetchData(String messageError);

    void renderErrorResponseFetchData(String messageError);

    void renderErrorConnection(String messageError);

    void renderErrorUnknown(String messageError);

    void renderUserNotLogin();
}

/*
 * Created By Kulomady on 9/30/16 6:14 PM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/30/16 6:14 PM
 */

package com.arm.hackbri.landmoney.view;

public interface CreateDebitView {

    void renderErrorConnection(String messageError);

    void renderErrorUnknown(String messageError);

    String getPhoneNumber();

    int getAmount();

    String getDescription();

    void successCreateDebit();
}
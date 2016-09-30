/*
 * Created By Kulomady on 9/30/16 6:00 PM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/30/16 6:00 PM
 */

package com.arm.hackbri.landmoney.view;

public interface CreateCreditView {

    void renderErrorConnection(String messageError);

    void renderErrorUnknown(String messageError);

    void successedCreateCredit();

    String getPhoneNumber();

    int getAmount();

    String getDescription();
}

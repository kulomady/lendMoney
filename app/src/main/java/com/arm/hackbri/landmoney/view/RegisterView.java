/*
 * Created By Kulomady on 9/30/16 5:47 PM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/30/16 5:47 PM
 */

package com.arm.hackbri.landmoney.view;

/**
 * LoginView
 * Created by anggaprasetiyo on 9/30/16.
 */

public interface RegisterView {

    void renderErrorConnection(String messageError);

    void renderErrorUnknown(String messageError);

    String getPhoneNumber();

    String getPassword();

    String getPasswordConf();

    String getFullName();

    String getKtp();

    String getEmailAddress();




}

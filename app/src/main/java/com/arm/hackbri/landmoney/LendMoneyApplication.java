package com.arm.hackbri.landmoney;

import android.app.Application;

import timber.log.Timber;

public class LendMoneyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}

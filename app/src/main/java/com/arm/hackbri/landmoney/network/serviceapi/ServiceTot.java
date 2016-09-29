package com.arm.hackbri.landmoney.network.serviceapi;

/**
 * ServiceTot
 * Created by anggaprasetiyo on 9/29/16.
 */
public class ServiceTot {
    private static ServiceTot ourInstance = new ServiceTot();

    public static ServiceTot getInstance() {
        return ourInstance;
    }

    private ServiceTot() {
    }
}

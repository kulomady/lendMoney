package com.arm.hackbri.landmoney.interactor;

import com.arm.hackbri.landmoney.model.ParamNetwork;
import com.arm.hackbri.landmoney.model.response.Profile;

import junit.framework.TestCase;

import java.util.concurrent.CountDownLatch;

/**
 * NetworkInteractorImplTest
 * Created by anggaprasetiyo on 9/30/16.
 */
public class NetworkInteractorImplTest extends TestCase {
    public void tearDown() throws Exception {
        profile = null;
    }

    private NetworkInteractor networkInteractor;
    private Profile profile;
    private final CountDownLatch latch = new CountDownLatch(1);

    public void setUp() throws Exception {
        super.setUp();
        networkInteractor = new NetworkInteractorImpl();
    }

    public void testGetDebitList() throws Exception {

    }

    public void testGetCreditList() throws Exception {

    }


    public void testGetTBankSaldo() throws Exception {

    }

    public void testTransfer() throws Exception {

    }

    public void testGetDialogNewDebit() throws Exception {

    }

    public void testGetDialogNewCredit() throws Exception {

    }

    public void testGetDebitDetail() throws Exception {

    }

    public void testGetCreditDetail() throws Exception {

    }

    public void testGetListRedeem() throws Exception {

    }

    public void testLogin() throws Exception {
        networkInteractor.login(new ParamNetwork.Builder().put("user_phone", "085747212168")
                .put("user_password", "password").build(), new OnFetchDataListener<Profile>() {
            @Override
            public void onSuccessFetchData(Profile data) {
                profile = data;
                latch.countDown();
            }

            @Override
            public void onFailedFetchData(Throwable throwable) {
                profile = null;
                latch.countDown();
            }
        });
        latch.await();
        assertNotNull(profile);
    }


}
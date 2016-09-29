package com.arm.hackbri.landmoney.view;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Kulomady on 9/29/16.
 */
public interface PinjamanListView {

    void showProgressFetchPinjamanList();

    void dismissProgressFetchPinjamanList();

    void renderDataPinjamanList(ArrayList<Objects> pinjaman);
    
}

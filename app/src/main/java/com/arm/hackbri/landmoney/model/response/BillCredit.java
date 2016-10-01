package com.arm.hackbri.landmoney.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * BillCredit
 * Created by anggaprasetiyo on 10/1/16.
 */

public class BillCredit implements Parcelable {
    @SerializedName("success")
    @Expose
    private int success;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.success);
    }

    public BillCredit() {
    }

    protected BillCredit(Parcel in) {
        this.success = in.readInt();
    }

    public static final Parcelable.Creator<BillCredit> CREATOR = new Parcelable.Creator<BillCredit>() {
        @Override
        public BillCredit createFromParcel(Parcel source) {
            return new BillCredit(source);
        }

        @Override
        public BillCredit[] newArray(int size) {
            return new BillCredit[size];
        }
    };
}

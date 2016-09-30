package com.arm.hackbri.landmoney.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * CreateDebitCredit
 * Created by anggaprasetiyo on 9/30/16.
 */

public class CreateDebitCredit implements Parcelable {
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

    public CreateDebitCredit() {
    }

    protected CreateDebitCredit(Parcel in) {
        this.success = in.readInt();
    }

    public static final Parcelable.Creator<CreateDebitCredit> CREATOR = new Parcelable.Creator<CreateDebitCredit>() {
        @Override
        public CreateDebitCredit createFromParcel(Parcel source) {
            return new CreateDebitCredit(source);
        }

        @Override
        public CreateDebitCredit[] newArray(int size) {
            return new CreateDebitCredit[size];
        }
    };
}

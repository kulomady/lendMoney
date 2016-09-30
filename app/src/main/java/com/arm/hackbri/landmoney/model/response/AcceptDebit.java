package com.arm.hackbri.landmoney.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * AcceptDebit
 * Created by anggaprasetiyo on 10/1/16.
 */

public class AcceptDebit implements Parcelable {
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

    public AcceptDebit() {
    }

    protected AcceptDebit(Parcel in) {
        this.success = in.readInt();
    }

    public static final Parcelable.Creator<AcceptDebit> CREATOR = new Parcelable.Creator<AcceptDebit>() {
        @Override
        public AcceptDebit createFromParcel(Parcel source) {
            return new AcceptDebit(source);
        }

        @Override
        public AcceptDebit[] newArray(int size) {
            return new AcceptDebit[size];
        }
    };
}

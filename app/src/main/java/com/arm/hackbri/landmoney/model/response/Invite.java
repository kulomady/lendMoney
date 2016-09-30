package com.arm.hackbri.landmoney.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Invite
 * Created by anggaprasetiyo on 10/1/16.
 */

public class Invite implements Parcelable {

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

    public Invite() {
    }

    protected Invite(Parcel in) {
        this.success = in.readInt();
    }

    public static final Parcelable.Creator<Invite> CREATOR = new Parcelable.Creator<Invite>() {
        @Override
        public Invite createFromParcel(Parcel source) {
            return new Invite(source);
        }

        @Override
        public Invite[] newArray(int size) {
            return new Invite[size];
        }
    };
}

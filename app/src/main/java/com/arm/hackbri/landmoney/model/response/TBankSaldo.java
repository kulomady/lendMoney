package com.arm.hackbri.landmoney.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * TBankSaldo
 * Created by anggaprasetiyo on 9/30/16.
 */

public class TBankSaldo implements Parcelable {

    @SerializedName("saldo")
    @Expose
    private String saldo;

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.saldo);
    }

    public TBankSaldo() {
    }

    protected TBankSaldo(Parcel in) {
        this.saldo = in.readString();
    }

    public static final Parcelable.Creator<TBankSaldo> CREATOR = new Parcelable.Creator<TBankSaldo>() {
        @Override
        public TBankSaldo createFromParcel(Parcel source) {
            return new TBankSaldo(source);
        }

        @Override
        public TBankSaldo[] newArray(int size) {
            return new TBankSaldo[size];
        }
    };
}

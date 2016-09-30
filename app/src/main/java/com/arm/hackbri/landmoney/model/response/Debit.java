package com.arm.hackbri.landmoney.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Debit
 * Created by anggaprasetiyo on 9/30/16.
 */

public class Debit implements Parcelable {
    public static int STATUS_PENDING = 1;
    public static int STATUS_COMPLETED = 0;
    public static int STATUS_UNPAID = -1;

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_pic")
    @Expose
    private String userPic;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("debt_amt")
    @Expose
    private Integer debtAmt;
    @SerializedName("debt_desc")
    @Expose
    private String debtDesc;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDebtAmt() {
        return debtAmt;
    }

    public void setDebtAmt(Integer debtAmt) {
        this.debtAmt = debtAmt;
    }

    public String getDebtDesc() {
        return debtDesc;
    }

    public void setDebtDesc(String debtDesc) {
        this.debtDesc = debtDesc;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.userName);
        dest.writeString(this.userPic);
        dest.writeValue(this.status);
        dest.writeValue(this.debtAmt);
        dest.writeString(this.debtDesc);
    }

    public Debit() {
    }

    protected Debit(Parcel in) {
        this.userId = in.readString();
        this.userName = in.readString();
        this.userPic = in.readString();
        this.status = (Integer) in.readValue(Integer.class.getClassLoader());
        this.debtAmt = (Integer) in.readValue(Integer.class.getClassLoader());
        this.debtDesc = in.readString();
    }

    public static final Parcelable.Creator<Debit> CREATOR = new Parcelable.Creator<Debit>() {
        @Override
        public Debit createFromParcel(Parcel source) {
            return new Debit(source);
        }

        @Override
        public Debit[] newArray(int size) {
            return new Debit[size];
        }
    };
}

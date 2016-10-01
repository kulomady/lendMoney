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
    public final static int STATUS_PENDING = -1;
    public final static int STATUS_COMPLETED = 0;
    public final static int STATUS_UNPAID = 1;

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
    @SerializedName("debt_id")
    @Expose
    private String debtId;
    @SerializedName("user_type")
    @Expose
    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

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

    public String getDebtId() {
        return debtId;
    }

    public void setDebtId(String debtId) {
        this.debtId = debtId;
    }

    public Debit() {
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
        dest.writeString(this.debtId);
        dest.writeString(this.userType);
    }

    protected Debit(Parcel in) {
        this.userId = in.readString();
        this.userName = in.readString();
        this.userPic = in.readString();
        this.status = (Integer) in.readValue(Integer.class.getClassLoader());
        this.debtAmt = (Integer) in.readValue(Integer.class.getClassLoader());
        this.debtDesc = in.readString();
        this.debtId = in.readString();
        this.userType = in.readString();
    }

    public static final Creator<Debit> CREATOR = new Creator<Debit>() {
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

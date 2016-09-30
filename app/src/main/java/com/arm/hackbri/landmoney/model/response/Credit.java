package com.arm.hackbri.landmoney.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Credit
 * Created by anggaprasetiyo on 9/30/16.
 */

public class Credit implements Parcelable {
    public static int STATUS_PENDING = -1;
    public static int STATUS_COMPLETED = 0;
    public static int STATUS_UNPAID = 1;

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
    @SerializedName("credit_amt")
    @Expose
    private Integer creditAmt;
    @SerializedName("credit_desc")
    @Expose
    private String creditDesc;

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

    public Integer getCreditAmt() {
        return creditAmt;
    }

    public void setCreditAmt(Integer creditAmt) {
        this.creditAmt = creditAmt;
    }

    public String getCreditDesc() {
        return creditDesc;
    }

    public void setCreditDesc(String creditDesc) {
        this.creditDesc = creditDesc;
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
        dest.writeValue(this.creditAmt);
        dest.writeString(this.creditDesc);
    }

    public Credit() {
    }

    protected Credit(Parcel in) {
        this.userId = in.readString();
        this.userName = in.readString();
        this.userPic = in.readString();
        this.status = (Integer) in.readValue(Integer.class.getClassLoader());
        this.creditAmt = (Integer) in.readValue(Integer.class.getClassLoader());
        this.creditDesc = in.readString();
    }

    public static final Parcelable.Creator<Credit> CREATOR = new Parcelable.Creator<Credit>() {
        @Override
        public Credit createFromParcel(Parcel source) {
            return new Credit(source);
        }

        @Override
        public Credit[] newArray(int size) {
            return new Credit[size];
        }
    };
}

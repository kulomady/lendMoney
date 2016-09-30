package com.arm.hackbri.landmoney.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Profile
 * Created by anggaprasetiyo on 9/30/16.
 */

public class Profile implements Parcelable {
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_password")
    @Expose
    private String userPassword;
    @SerializedName("user_img")
    @Expose
    private String userImg;
    @SerializedName("user_phone")
    @Expose
    private String userPhone;
    @SerializedName("user_ktp")
    @Expose
    private String userKtp;
    @SerializedName("user_ttl")
    @Expose
    private String userTtl;
    @SerializedName("user_address")
    @Expose
    private String userAddress;
    @SerializedName("user_city")
    @Expose
    private String userCity;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_pin")
    @Expose
    private String userPin;
    @SerializedName("user_rek")
    @Expose
    private String userRek;
    private TBankSaldo tBankSaldo;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserKtp() {
        return userKtp;
    }

    public void setUserKtp(String userKtp) {
        this.userKtp = userKtp;
    }

    public String getUserTtl() {
        return userTtl;
    }

    public void setUserTtl(String userTtl) {
        this.userTtl = userTtl;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPin() {
        return userPin;
    }

    public void setUserPin(String userPin) {
        this.userPin = userPin;
    }

    public String getUserRek() {
        return userRek;
    }

    public void setUserRek(String userRek) {
        this.userRek = userRek;
    }


    public Profile() {
    }

    public TBankSaldo gettBankSaldo() {
        return tBankSaldo;
    }

    public void settBankSaldo(TBankSaldo tBankSaldo) {
        this.tBankSaldo = tBankSaldo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.userId);
        dest.writeString(this.userName);
        dest.writeString(this.userPassword);
        dest.writeString(this.userImg);
        dest.writeString(this.userPhone);
        dest.writeString(this.userKtp);
        dest.writeString(this.userTtl);
        dest.writeString(this.userAddress);
        dest.writeString(this.userCity);
        dest.writeString(this.userEmail);
        dest.writeString(this.userPin);
        dest.writeString(this.userRek);
        dest.writeParcelable(this.tBankSaldo, flags);
    }

    protected Profile(Parcel in) {
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userName = in.readString();
        this.userPassword = in.readString();
        this.userImg = in.readString();
        this.userPhone = in.readString();
        this.userKtp = in.readString();
        this.userTtl = in.readString();
        this.userAddress = in.readString();
        this.userCity = in.readString();
        this.userEmail = in.readString();
        this.userPin = in.readString();
        this.userRek = in.readString();
        this.tBankSaldo = in.readParcelable(TBankSaldo.class.getClassLoader());
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel source) {
            return new Profile(source);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };
}

package com.naaniz.naanizcustomer.network.objects;

import com.google.gson.annotations.SerializedName;

public class LoginUserDetails {


    @SerializedName("uuid")
    private String firebaseUID;

    public LoginUserDetails(String firebaseUID) {
        this.firebaseUID = firebaseUID;
    }

    public String getFirebaseUID() {
        return firebaseUID;
    }


    public void setFirebaseUID(String firebaseUID) {
        this.firebaseUID = firebaseUID;
    }
}

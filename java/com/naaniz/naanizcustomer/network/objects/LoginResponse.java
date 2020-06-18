package com.naaniz.naanizcustomer.network.objects;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("token")
    private String authToken;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}

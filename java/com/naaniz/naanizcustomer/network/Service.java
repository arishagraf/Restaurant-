package com.naaniz.naanizcustomer.network;

import android.widget.CalendarView;

import com.naaniz.naanizcustomer.models.favorites;
import com.naaniz.naanizcustomer.network.objects.LoginResponse;
import com.naaniz.naanizcustomer.network.objects.LoginUserDetails;
import com.naaniz.naanizcustomer.network.objects.Test;
import com.naaniz.naanizcustomer.network.objects.UserDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Service {

    @POST("users/tokenize")
    Call<LoginResponse> signUpUser(@Body UserDetails userDetails);

    @POST("users/tokenize")
    Call<LoginResponse> logInUser(@Body LoginUserDetails userDetails);

    @POST("users/tokenize")
    Call<LoginResponse> test(@Body Test test);


    @POST()
    Call<List<favorites>> SavedItems() ;
}

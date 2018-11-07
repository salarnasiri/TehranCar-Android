package com.sqtehrancar.tehrancarandroid.contorller.network;

import com.sqtehrancar.tehrancarandroid.model.LoginInfo;
import com.sqtehrancar.tehrancarandroid.model.User;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {


    @POST("/app/login")
    Call<User> loginUser(@Body LoginInfo user);
}
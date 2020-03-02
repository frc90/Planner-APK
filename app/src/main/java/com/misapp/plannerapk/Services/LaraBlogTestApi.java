package com.misapp.plannerapk.Services;

import com.misapp.plannerapk.Model.User;
import com.misapp.plannerapk.Routes.Routes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LaraBlogTestApi {

    // 192.168.101.1:8080/larablog/public/users
    @GET(Routes.USERS)
    Call<List<User>> getUsers();
}

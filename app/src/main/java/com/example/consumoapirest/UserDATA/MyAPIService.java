package com.example.consumoapirest.UserDATA;

import com.example.consumoapirest.UResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPIService {
    @GET("Obtener_user.php")
    Call<USEResponse> getUsers();
}


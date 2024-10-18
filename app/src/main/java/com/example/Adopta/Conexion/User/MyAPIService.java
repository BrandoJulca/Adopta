package com.example.Adopta.Conexion.User;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPIService {
    @GET("Obtener_user.php")
    Call<UResponse> getUser();
}


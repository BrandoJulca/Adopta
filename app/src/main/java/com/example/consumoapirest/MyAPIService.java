package com.example.consumoapirest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPIService {
    @GET("Obtener_universidad.php")
    Call<UResponse> getUniversidades();
}


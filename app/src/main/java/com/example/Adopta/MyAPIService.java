package com.example.Adopta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPIService {
    @GET("Obtener_universidad.php")
    Call<UResponse> getUniversidades();
}


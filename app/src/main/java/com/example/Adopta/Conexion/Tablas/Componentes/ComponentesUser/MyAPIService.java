package com.example.Adopta.Conexion.Tablas.Componentes.ComponentesUser;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPIService {
    @GET("Obtener_user.php")
    Call<UResponse> getUser();
}


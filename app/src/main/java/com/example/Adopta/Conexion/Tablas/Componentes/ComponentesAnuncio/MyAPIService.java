package com.example.Adopta.Conexion.Tablas.Componentes.ComponentesAnuncio;
import com.example.Adopta.Conexion.Tablas.Componentes.ComponentesAnuncio.UResponse;


import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPIService {
    @GET("Obtener_anuncio.php")
    Call<UResponse> getAnuncios();
}


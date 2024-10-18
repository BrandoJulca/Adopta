package Conexion.Anuncio.ComponentesAnuncio;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPIService {
    @GET("Obtener_user.php")
    Call<UResponse> getUser();
}


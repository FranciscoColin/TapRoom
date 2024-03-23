package com.desarrollosPatito.taproom.Catalogo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCatalogo {
    @GET("getCervezas.php") // Cambia "tu_endpoint.php" por la ruta a tu archivo PHP
    Call<List<ObjCerveza>> getCervezas();
    @GET("updateDelete.php") // Cambia esto por la ruta real a tu script PHP
    Call<ResponseDelete> actualizarRegistro(@Query("id") String id);
}

package com.desarrollosPatito.taproom.Catalogo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCatalogo {
    @GET("getCervezas.php") // Cambia "tu_endpoint.php" por la ruta a tu archivo PHP
    Call<List<ObjCerveza>> getCervezas();
}

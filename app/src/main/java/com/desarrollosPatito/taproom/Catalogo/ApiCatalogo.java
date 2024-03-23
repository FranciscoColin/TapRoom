package com.desarrollosPatito.taproom.Catalogo;

import com.desarrollosPatito.taproom.Catalogo.Edita.ObjCerveza;
import com.desarrollosPatito.taproom.Catalogo.Edita.ResponseEdit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiCatalogo {
    @GET("getCervezas.php")
    Call<List<com.desarrollosPatito.taproom.Catalogo.ObjCerveza>> getCervezas();
    @GET("updateDelete.php")
    Call<ResponseDelete> actualizarRegistro(@Query("id") String id);
    @GET("updateCerveza.php")
    Call<ResponseEdit> updateBeer(@Query("id") String id,
                                 @Query("beerName") String beerName,
                                 @Query("beerStyle") String beerStyle,
                                 @Query("abv") String abv,
                                 @Query("ibu") String ibu,
                                 @Query("flavorDescription") String flavorDescription,
                                 @Query("brewery") String brewery,
                                 @Query("servingSize") String servingSize,
                                 @Query("price") String price,
                                 @Query("origin") String origin,
                                 @Query("status") String status);
}

package com.desarrollosPatito.taproom.Nueva;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiNueva {
    @FormUrlEncoded
    @POST("insertCerveza.php")
    Call<ReponseNueva> insertBeer(
            @Field("beerName") String beerName,
            @Field("beerStyle") String beerStyle,
            @Field("abv") double abv,
            @Field("ibu") int ibu,
            @Field("flavorDescription") String flavorDescription,
            @Field("brewery") String brewery,
            @Field("servingSize") String servingSize,
            @Field("price") double price,
            @Field("origin") String origin,
            @Field("status") String status
    );
}

package com.desarrollosPatito.taproom.Utilidades;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClienteTapRoom {
    private static final String BASE_URL = "https://desarrollospatito.com/project/TapRoom/endpoint/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

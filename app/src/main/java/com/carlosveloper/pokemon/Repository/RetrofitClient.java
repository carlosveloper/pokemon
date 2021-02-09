package com.carlosveloper.pokemon.Repository;



import com.carlosveloper.pokemon.Common.Constantes;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance = null;
    private ApiService miniService;
    private Retrofit retrofit;

    public RetrofitClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .build();

        retrofit = new Retrofit.Builder()
                .client(client)

                .baseUrl(Constantes.UrlBase)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        miniService = retrofit.create(ApiService.class);
    }

    // Patrón Singleton
    public static RetrofitClient getInstance() {
        if(instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public ApiService getMiniService() {
        return miniService;
    }

}

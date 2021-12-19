package com.gov.sa.swcc;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RetrofitClient {

    private static RetrofitClient instance = null;
    private Api myApi;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(Api.class);
    }

    private RetrofitClient(String URL) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(Api.class);
    }



    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public static synchronized RetrofitClient getInstance(String URL) {
        if (instance == null) {
            instance = new RetrofitClient(URL);
        }

        return instance;
    }

    public Api getMyApi() {
        return myApi;
    }
}
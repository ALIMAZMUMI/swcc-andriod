package com.gov.sa.swcc;

import android.util.Base64;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

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
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
         OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                 .addInterceptor(logging)
                .build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        myApi = retrofit.create(Api.class);
    }



    public static synchronized RetrofitClient getInstance() {
            instance = new RetrofitClient();

        return instance;
    }

    public static synchronized RetrofitClient getInstance(String URL) {
            instance = new RetrofitClient(URL);
        return instance;
    }

    public Api getMyApi() {
        return myApi;
    }
}
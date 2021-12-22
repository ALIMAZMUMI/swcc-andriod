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
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(Api.class);
    }

    private RetrofitClient(String URL,String Auth) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Strategy strategy = new AnnotationStrategy();
        Serializer serializer = new Persister(strategy);


//        OkHttpClient.Builder client = new OkHttpClient.Builder();
//        client.readTimeout(60, TimeUnit.SECONDS);
//        client.writeTimeout(60, TimeUnit.SECONDS);
//        client.connectTimeout(60, TimeUnit.SECONDS);
//        client.addInterceptor(interceptor);
//        client.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                byte[] data = "P2001713316:sap@1234".getBytes(StandardCharsets.UTF_8);
//                String base64 = Base64.encodeToString(data, Base64.DEFAULT);
//                    request = request
//                            .newBuilder()
//                            .addHeader("Authorization","Basic UDIwMDE3MTMzMTY6c2FwQDEyMzQ=")
//                            .build();
//
//                return chain.proceed(request);
//            }
//        });
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit =  new Retrofit.Builder()
                .addConverterFactory(SimpleXmlConverterFactory.create(serializer))
                .baseUrl(URL).client(okHttpClient)
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
    public static synchronized RetrofitClient getSAPInstance(String URL) {
        if (instance == null) {
            instance = new RetrofitClient(URL,"");
        }

        return instance;
    }
    public Api getMyApi() {
        return myApi;
    }
}
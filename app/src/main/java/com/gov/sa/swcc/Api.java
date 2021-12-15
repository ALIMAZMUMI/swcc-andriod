package com.gov.sa.swcc;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;

public interface Api {
//urlString = @"https://ext.swcc.gov.sa/OTP/API/ADOperationsOTP/auth/";
    String BASE_URL = "https://ext.swcc.gov.sa/OTP/API/";

    @GET("ADOperationsOTP/auth/")
    Call<LoginResult> Login(@Header("UserName") String username, @Header("UserPass") String pass,@Header("OtpCode") String otp);
}

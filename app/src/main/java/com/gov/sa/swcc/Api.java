package com.gov.sa.swcc;

import com.gov.sa.swcc.model.LoginResult;
import com.gov.sa.swcc.model.PersonalResult;
import com.gov.sa.swcc.model.TransactionsApiResult;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;

public interface Api {
//urlString = @"https://ext.swcc.gov.sa/OTP/API/ADOperationsOTP/auth/";
    String BASE_URL = "https://ext.swcc.gov.sa/OTP/API/";
    String SingINOutBASE_URL = "https://ext.swcc.gov.sa/TransactionsApi/api/";
    String SAPBASE_URL = "https://l650075-iflmap.hcisbp.sa1.hana.ondemand.com/cxf/";

    @GET("ADOperationsOTP/auth/")
    Call<LoginResult> Login(@Header("UserName") String username, @Header("UserPass") String pass, @Header("OtpCode") String otp);


    @GET("ADOperationsOTP/auth/")
    Call<PersonalResult> LoginOTP(@Header("UserName") String username, @Header("UserPass") String pass, @Header("OtpCode") String otp);


    @GET("EmpTrans")
    Call<List<TransactionsApiResult>> Transactions(@Header("UserName") String username);

    @GET("EmpTrans")
    Call<List<TransactionsApiResult>> Transactions(@Header("UserName") String username);





}

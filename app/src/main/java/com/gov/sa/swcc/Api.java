package com.gov.sa.swcc;

import com.gov.sa.swcc.model.LoginResult;
import com.gov.sa.swcc.model.PersonalResult;
import com.gov.sa.swcc.model.TransactionsApiResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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

//    @Headers({
//            "Content-Type: text/xml",
//            "Content-Length: "
//    })
//    @POST("employeeLeavesService2MobileApp")
//    Call<LeaveBody> Leaves(@Header("Authorization") String authkey, @Body Envelope envelope);





}

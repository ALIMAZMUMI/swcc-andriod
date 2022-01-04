package com.gov.sa.swcc;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.gov.sa.swcc.model.ITRequest;
import com.gov.sa.swcc.model.ITRoot;
import com.gov.sa.swcc.model.InsuranceInfo;
import com.gov.sa.swcc.model.LoginResult;
import com.gov.sa.swcc.model.PersonalResult;
import com.gov.sa.swcc.model.ProPlanning;
import com.gov.sa.swcc.model.TransactionsApiResult;

import org.json.JSONArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
//urlString = @"https://ext.swcc.gov.sa/OTP/API/ADOperationsOTP/auth/";
    String BASE_URL = "https://ext.swcc.gov.sa/OTP/API/";
    String SingINOutBASE_URL = "https://ext.swcc.gov.sa/TransactionsApi/api/";
    String Ticket = "https://apitest.swcc.gov.sa/swccmobile/api/FootPrint/";
String Insur="https://ext.swcc.gov.sa/insinfo/api/";

    String ProPlaning="https://ext.swcc.gov.sa/ocpapi/api/ocp/";

    @GET("ADOperationsOTP/auth/")
    Call<LoginResult> Login(@Header("UserName") String username, @Header("UserPass") String pass, @Header("OtpCode") String otp);


    @GET("ADOperationsOTP/auth/")
    Call<PersonalResult> LoginOTP(@Header("UserName") String username, @Header("UserPass") String pass, @Header("OtpCode") String otp);


    @GET("EmpTrans")
    Call<List<TransactionsApiResult>> Transactions(@Header("UserName") String username);



    @Headers({"Accept: application/json","Content-Type: application/json"})
    @POST("CreateITRequest")
    Call<String> ITRequest(@Body ITRequest itRequest, @Header("apitoken") String apitoken);


    @Headers({"Accept: application/json","Content-Type: application/json"
    ,"apitoken: 26EAADIT-310F-48E4-87C6-C827E73A4A00"})
    @POST("CreateITRequest")
    Call<String> ITRequest(@Body String itRequest);



    @Headers({"Accept: application/json","Content-Type: application/json"
            ,"token: 01994397066794731552"})
    @GET("InsuranceInfo")
    Call<InsuranceInfo> Insurance(@Header("UserName") String username);





    @GET("Get_Export_Mobile")
    Call<ProPlanning> ProPlaning(@Query("prm_from_date") String Date);







}

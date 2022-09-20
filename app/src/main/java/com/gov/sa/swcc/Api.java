package com.gov.sa.swcc;


import com.gov.sa.swcc.model.Chatbot.Chatbotrequest;
import com.gov.sa.swcc.model.Chatbot.Chatbotres;
import com.gov.sa.swcc.model.CompleteTask.CompleteTask;
import com.gov.sa.swcc.model.CreateTask.CreateTask;
import com.gov.sa.swcc.model.CreateTask.CreateTaskResponce;
import com.gov.sa.swcc.model.EditTask.EditTask;
import com.gov.sa.swcc.model.EmployeeTaskDetails.EmployeeTaskDetails;
import com.gov.sa.swcc.model.ExtendTask.ExtendTask;
import com.gov.sa.swcc.model.ExtendTimeHistory.ExtendTimeHistory;
import com.gov.sa.swcc.model.GetToken.GetToken;
import com.gov.sa.swcc.model.HirereachyManager;
import com.gov.sa.swcc.model.ITRequest;
import com.gov.sa.swcc.model.ITRoot;
import com.gov.sa.swcc.model.InsuranceInfo;
import com.gov.sa.swcc.model.InternalType;
import com.gov.sa.swcc.model.LoginResult;
import com.gov.sa.swcc.model.MangerCount.MangerCount;
import com.gov.sa.swcc.model.OTPRes;
import com.gov.sa.swcc.model.PartnerModel;
import com.gov.sa.swcc.model.PersonalResult;
import com.gov.sa.swcc.model.ProPlanning;
import com.gov.sa.swcc.model.Rating.PendingRatingRequests;
import com.gov.sa.swcc.model.RatingModel;
import com.gov.sa.swcc.model.ReportModel;
import com.gov.sa.swcc.model.ReportRes.ReportRe;
import com.gov.sa.swcc.model.SearchEmpItem;
import com.gov.sa.swcc.model.Sharekproject;
import com.gov.sa.swcc.model.Signproject;
import com.gov.sa.swcc.model.TaskEmpManager.TaskEmpManager;
import com.gov.sa.swcc.model.TaskHistory.TaskHistory;
import com.gov.sa.swcc.model.TransactionsApiResult;
import com.gov.sa.swcc.model.emptask.EmpTasks;
import com.gov.sa.swcc.model.locationAttend.SWCCLocations;
import com.gov.sa.swcc.model.locationAttend.TransactionLocationData;

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

    String Domain="apiext.swcc.gov.sa";
    //String Domain="apitest.swcc.gov.sa";

    String Global="https://"+Domain+"/GatewayControlPanel/";
    //String GlobalP="https://apiext.swcc.gov.sa/GatewayControlPanel/";

    String TaskURL="https://"+Domain+"/TaskTest/api/";
    String BASE_URL = "https://"+Domain+"/APIOTP/API/";
    String Sharektest="https://"+Domain+"/testapp/";
    String LOCATION_ATT_URL = "https://"+Domain+"/";
    String FURL = "https://"+Domain+"/";

    //    String SingINOutBASE_URL = "https://ext.swcc.gov.sa/TransactionsApi/api/";
//    String Ticket = "https://apiext.swcc.gov.sa/swccmobile/api/FootPrint/";
//    String Insur="https://ext.swcc.gov.sa/insinfo/api/";
//    //String Sharek="https://apitest.swcc.gov.sa/FupsApi/api/EmployeeEvaluation/";
//    String Sharek="https://ext.swcc.gov.sa/FupsApi/api/EmployeeEvaluation/";
    String SharekKey="123456789";

    String ProPlaning=Global+"OCP/";

    @GET("ADOperationsOTP/auth/")
    Call<LoginResult> Login(@Header("UserName") String username, @Header("UserPass") String pass, @Header("OtpCode") String otp);

    @GET("ADOperationsOTP/auth/")
    Call<OTPRes> LoginO(@Header("UserName") String username, @Header("UserPass") String pass, @Header("OtpCode") String otp);


    @GET("ADOperationsOTP/auth/")
    Call<PersonalResult> LoginOTP(@Header("UserName") String username, @Header("UserPass") String pass, @Header("OtpCode") String otp, @Header("MobileAppId") String MobileAppId);

    @GET("Employee/EmpTrans")
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
    @GET("Insurance/InsuranceInfo")
    Call<InsuranceInfo> Insurance(@Header("UserName") String username);


    @GET("OCP_ProducationDataService")
    Call<ProPlanning> ProPlaning(@Query("prm_from_date") String Date);


//sqJfkhJSn5
    @Headers({"Accept: application/json","Content-Type: application/json"
            ,"secretToken: 123456789"})
    @GET("Shareek/EmployeeEvaluation/GetAllSupervisorProjecct")
    Call<List<Sharekproject>> GetAllSupervisorProjecct(@Query("SupervisorUID") String ID);

    @Headers({"Accept: application/json","Content-Type: application/json"
            ,"secretToken: 123456789"})
    @GET("Shareek/EmployeeEvaluation/GetEmployeeTransactions")
    Call<List<Signproject>> GetEmployeeTransactions(@Query("SuperVisorId") String ID, @Query("date") String Date);



    @Headers({"Accept: application/json","Content-Type: application/json"
            ,"secretToken: 123456789"})
    @GET("Shareek/EmployeeEvaluation/GetAllLookupEvaluation")
    Call<List<RatingModel>> GetAllLookupEvaluation(@Query("SupervisorUID") String ID);




    @Headers({"Accept: application/json","Content-Type: application/json"
            ,"secretToken: 123456789"})
    @POST("Shareek/EmployeeEvaluation/InsertEmployeeEvaluation")
    Call<Boolean> InsertContractsEvaluation(@Body List<RatingModel> ratingModel);



    @Headers({"Accept: application/json","Content-Type: application/json"
            ,"secretToken: 123456789"})
    @GET("Shareek/EmployeeEvaluation/GetAllContractElementsEvaluation")
    Call<List<PartnerModel>> GetAllContractElementsEvaluation(@Query("ProjectMangerId") String ProjectMangerId
    ,@Query("SupervisorId") String SupervisorId
    ,@Query("Classfcation_LK") String Classfcation_LK
    ,@Query("roundNumber") String roundNumber
    ,@Query("date") String date);



    @Headers({"Accept: application/json","Content-Type: application/json"
            ,"secretToken: 123456789"})
    @POST("Shareek/EmployeeEvaluation/InsertContractsEvaluation")
    Call<Boolean> InsertContractsEvaluation1(@Body List<PartnerModel> partnerModels);




    @Headers({"Accept: application/json","Content-Type: application/json"
            ,"secretToken: 123456789"})
    @GET("Shareek/EmployeeEvaluation/GetReportProjects")
    Call<List<ReportModel>> GetReportProjects(@Query("UID") String ID);

    @GET("Employee/SwccEmps")
    Call<SearchEmpItem> SwccEmps(@Query("EmpSearch") String EmpSearch,@Query("tokan") String tokan);


    @GET("GetCategories")
    Call<InternalType> GetCategories(@Query("token") String token);
//----------------New
@Headers({"Accept: application/json","Content-Type: application/json"
        ,"secretToken: 123456789"})
@GET("Shareek/EmployeeEvaluation/GetAllProject")
Call<List<ReportRe>> GetAllProject(@Query("UID") String UID);


    @Headers({"Accept: application/json","Content-Type: application/json"
            ,"secretToken: 123456789"})
    @GET("Shareek/EmployeeEvaluation/GetDDL")
    Call<List<ReportRe>> GetDDL(@Query("UID") String UID,@Query("ContractsId") String ContractsId,@Query("RoleType") String RoleType);

//EmpTask

    @Headers({"Accept: application/json","Content-Type: application/json"})
    @GET("EmpTaskWatcher/GetAllMyTasks")
    Call<EmpTasks> GetAllMyTasks(@Header("Authorization") String Authorization);

    @Headers({"Accept: application/json","Content-Type: application/json"})
    @GET("Account/GetToken")
    Call<GetToken> GetToken(@Header("uid") String uid, @Header("apptoken") String apptoken,@Header("DeviceId") String DeviceID );



    @Headers({"Accept: application/json","Content-Type: application/json"})
    @GET("EmpTaskManagment/GetEmployeeTaskDetails?")
    Call<EmployeeTaskDetails> GetEmployeeTaskDetails(@Header("Authorization") String Authorization, @Query("EmpTaskId") String TID);

    @Headers({"Accept: application/json","Content-Type: application/json"})
    @GET("EmpTaskWatcher/GetExtendTimeHistory?")
    Call<ExtendTimeHistory> GetExtendTimeHistory(@Header("Authorization") String Authorization, @Query("EmpTaskId") String TID);


    @Headers({"Accept: application/json","Content-Type: application/json"})
    @GET("EmpTaskWatcher/GetTaskHistory?")
    Call<TaskHistory> GetTaskHistory(@Header("Authorization") String Authorization, @Query("EmpTaskId") String TID);

    @Headers({"Accept: application/json","Content-Type: application/json"})
    @GET("EmpTaskManagment/ExtendTaskTimeRequest?")
    Call<ExtendTask> ExtendTaskTimeRequest(@Header("Authorization") String Authorization, @Query("EmpTaskId") String TID, @Query("ExtendComment") String ExtendComment);



    @Headers({"Accept: application/json","Content-Type: application/json"})
    @POST("EmpTaskManagment/CompleteTask")
    Call<ExtendTask> CompleteTask(@Header("Authorization") String Authorization, @Body CompleteTask completeTask);


    @Headers({"Accept: application/json","Content-Type: application/json"})
    @GET("EmpTaskManagment/ReturnTask?")
    Call<ExtendTask> ReturnTask(@Header("Authorization") String Authorization, @Query("EmpTaskId") String TID, @Query("ReturnComment") String ExtendComment);

    @Headers({"Accept: application/json","Content-Type: application/json"})
    @GET("ManagerTaskManagment/ApproveTask?")
    Call<ExtendTask> ApproveTask(@Header("Authorization") String Authorization, @Query("EmpTaskId") String TID);


    @Headers({"Accept: application/json","Content-Type: application/json"})
    @GET("ManagerTaskManagment/GetTaskHistory?")
    Call<TaskHistory> GetMTaskHistory(@Header("Authorization") String Authorization, @Query("EmpTaskId") String TID);



    @Headers({"Accept: application/json","Content-Type: application/json"})
    @GET("ManagerTaskManagment/ApproveExtendTask?")
    Call<ExtendTask> ApproveExtendTask(@Header("Authorization") String Authorization, @Query("EmpTaskId") String TID);


    @Headers({"Accept: application/json","Content-Type: application/json"})
    @GET("ManagerTaskManagment/RejectTask?")
    Call<ExtendTask> RejectTask(@Header("Authorization") String Authorization, @Query("EmpTaskId") String TID);


    //MangerTask

    @Headers({"Accept: application/json","Content-Type: application/json"})
    @GET("ManagerTaskWatcher/GetAllTasks")
    Call<TaskEmpManager> GetAllTasksMG(@Header("Authorization") String Authorization);



    @Headers({"Accept: application/json","Content-Type: application/json"})
    @GET("ManagerTaskWatcher/GetPendingRatingRequests")
    Call<PendingRatingRequests> GetPendingRatingRequests(@Header("Authorization") String Authorization);

    @Headers({"Accept: application/json","Content-Type: application/json"})
    @GET("ManagerTaskWatcher/GetPendingRequests")
    Call<PendingRatingRequests> GetPendingRequests(@Header("Authorization") String Authorization);




    @Headers({"Accept: application/json","Content-Type: application/json"})
    @GET("ManagerTaskWatcher/GetPendingExtendRequests")
    Call<PendingRatingRequests> GetPendingExtendRequests(@Header("Authorization") String Authorization);



    @Headers({"Accept: application/json","Content-Type: application/json"})
    @GET("ManagerTaskWatcher/GetCompletedTasks")
    Call<PendingRatingRequests> GetCompletedTasks(@Header("Authorization") String Authorization);




    @Headers({"Accept: application/json","Content-Type: application/json"})
    @GET("ManagerTaskManagment/DeleteEmployeeTask")
    Call<ExtendTask> DeleteEmployeeTask(@Header("Authorization") String Authorization,@Query("EmpTaskId") String TID);



    @Headers({"Accept: application/json","Content-Type: application/json"})
    @GET("ManagerTaskManagment/RateTask")
    Call<ExtendTask> RateTask(@Header("Authorization") String Authorization,@Query("EmpTaskId") String TID,@Query("enum_EvaluationRates") String enum_EvaluationRates);

    //




    @Headers({"Accept: application/json","Content-Type: application/json"})
    @POST("ManagerTaskManagment/CreateTasks")
    Call<CreateTaskResponce> CreateTasks(@Header("Authorization") String Authorization, @Body CreateTask createTask);


    @Headers({"Accept: application/json","Content-Type: application/json"})
    @POST("ManagerTaskManagment/EditTask")
    Call<CreateTaskResponce> EditTask(@Header("Authorization") String Authorization, @Body EditTask createTask);


//






    @Headers({"Accept: application/json","Content-Type: application/json"
            ,"appToken: FFA1E000575C4762A6C0936D6455EA98"})
    @GET("Employee/HirereachyManager")
    Call<List<HirereachyManager>> HirereachyManager(@Query("uid") String ID);

    @Headers({"Accept: application/json","Content-Type: application/json"})
    @GET("ManagerTaskWatcher/GetManagerTasksCount")
    Call<MangerCount> GetManagerTasksCount(@Header("Authorization") String Authorization);




    @Headers({"Accept: application/json","Content-Type: application/json"})
    @GET("AttendanceLocation/AttendanceLocation/SwccLocations")
    Call<SWCCLocations> GetSWCCLocations(@Header("Authorization") String Authorization,@Query("UID") String UID);

//    @Headers({"Accept: application/json","Content-Type: application/json"})
//    @GET("AttendanceLocation/AttendanceLocation/LastTransactionLocation")
//    Call<TransactionLocationData> GetLastTransactionLocation(@Header("Authorization") String Authorization, @Query("uid") String ID);

    //https://apiTEST.swcc.gov.sa/AttendanceLocation/AttendanceLocation/InsertAttendance?Userlatitude=26.701928977470384&Userlongitude=46.68057949985196&uid=u190250&typeTransaction=out
    @Headers({"Accept: application/json","Content-Type: application/json","Platform: android"})
    @POST("AttendanceLocation/AttendanceLocation/InsertAttendance")
    Call<TransactionLocationData> InsertAttendance(@Header("Authorization") String Authorization, @Query("Userlatitude") String lat,@Query("Userlongitude") String lng,@Query("uid") String uid,@Query("typeTransaction") String type);


    @Headers({"Accept: application/json","Content-Type: application/json","Platform: android"})
    @POST("FinancialCalculator/api/CalculateEndOfService")
    Call<Double> CalculateEndOfService(@Header("Authorization") String Authorization, @Query("userSalary") String userSalary,@Query("userTotalAllowances") String userTotalAllowances,@Query("startWorkingDate") String startWorkingDate,@Query("endWorkingDate") String endWorkingDate);


    @Headers({"Accept: application/json","Content-Type: application/json","Platform: android"})
    @POST("FinancialCalculator/api/CalculateVacation")
    Call<Double> CalculateVacation(@Header("Authorization") String Authorization, @Query("userSalary") String userSalary,@Query("userTotalAllowances") String userTotalAllowances,@Query("userTotalDays") String userTotalDays);


    @Headers({"Accept: application/json","Content-Type: application/json","Platform: android"})
    @POST("Token/Insert")
    Call<Chatbotres> Chatbot(@Body Chatbotrequest chatbotrequest);



}

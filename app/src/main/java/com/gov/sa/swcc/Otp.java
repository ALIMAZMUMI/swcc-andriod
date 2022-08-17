package com.gov.sa.swcc;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gov.sa.swcc.model.GetToken.GetToken;
import com.gov.sa.swcc.model.LoginResult;
import com.gov.sa.swcc.model.MangerCount.MangerCount;
import com.gov.sa.swcc.model.PersonalResult;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class Otp extends Fragment {


    public Otp() {
        // Required empty public constructor
    }

    TextView Mobmsg,resend;
    ProgressBar resend_pro;
    Button sub_otp;
    EditText otp;
Global global;
    CountDownTimer Timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
global=new Global(getContext());
        View view=inflater.inflate(R.layout.fragment_otp, container, false);
        Mobmsg=(TextView)view.findViewById(R.id.Mobmsg);
        resend_pro=(ProgressBar) view.findViewById(R.id.resend_pro);
        resend=(TextView)view.findViewById(R.id.resend);
        sub_otp=(Button) view.findViewById(R.id.sub_otp);
        otp=(EditText) view.findViewById(R.id.otp);


        String Mobile="";
        Bundle bundle = this.getArguments();
        if (bundle != null) {
             Mobile = bundle.getString("Mobile","");
        }
        try {
            Mobmsg.setText("تم إرسال الرمز للجوال ******"+Mobile.substring(Mobile.length()-4));
        }catch (Exception ex){

        }
        resend.setVisibility(View.GONE);
        resend_pro.setVisibility(View.VISIBLE);


         Timer=new CountDownTimer(15000, 1000) {

            public void onTick(long millisUntilFinished) {
                resend.setVisibility(View.GONE);
            }

            public void onFinish() {
                resend.setVisibility(View.VISIBLE);
                resend_pro.setVisibility(View.GONE);
            }

        };

        Timer.start();

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(global.CheckInternet()) {
                }else{
                CallLogin();
                }

            }
        });

        sub_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(global.CheckInternet()) {
                }else{
                CallLoginOTP(otp.getText().toString());}
            }
        });


        otp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
//
                if(otp.getText().length()>=4){
                    sub_otp.setBackgroundResource(R.drawable.blueroundfull);
                    sub_otp.setEnabled(true);
                }else{
                    sub_otp.setBackgroundResource(R.drawable.grayroundbtn);
                    sub_otp.setEnabled(false);
                }
            }
        });

        sub_otp.setBackgroundResource(R.drawable.grayroundbtn);
        sub_otp.setEnabled(false);








        return view;
    }


    private void CallLogin() {
        String user=global.GetValue("Username");
        String pass=global.GetValue("Password");

        byte[] data = pass.getBytes(StandardCharsets.UTF_8);
        String base64 = Base64.encodeToString(data, Base64.DEFAULT);

        Log.d("base64",base64+"");
        Call<LoginResult> call = RetrofitClient.getInstance(Api.Global).getMyApi().Login(user,base64.trim(),"null");

        //Call<LoginResult> call = RetrofitClient.getInstance().getMyApi().Login(user,base64.trim(),"null");
        PorgressDilog dialog =  new PorgressDilog(getActivity());
        dialog.show();
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                Log.d("Resp",response.message()+"");
                dialog.dismiss();
                if(response.isSuccessful())
                {


                    if(response.body().getResultCode().equals("105")){
                        Timer.start();
                        resend.setVisibility(View.GONE);
                        resend_pro.setVisibility(View.VISIBLE);
                    }else {
                        global.ShowMessage(response.body().getResultMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }



        });
    }


    private void CallLoginOTP(String Otp) {
        String user=global.GetValue("Username");
        String pass=global.GetValue("Password");

        byte[] data = pass.getBytes(StandardCharsets.UTF_8);
        String base64 = Base64.encodeToString(data, Base64.DEFAULT);

        Log.d("base64",base64+"");

        Call<PersonalResult> call = RetrofitClient.getInstance(Api.Global).getMyApi().LoginOTP(user,base64.trim(),Otp,"");
        PorgressDilog dialog =  new PorgressDilog(getActivity());
        dialog.show();
        call.enqueue(new Callback<PersonalResult>() {
            @Override
            public void onResponse(Call<PersonalResult> call, Response<PersonalResult> response) {
                Log.d("Resp",response.message()+"");
                dialog.dismiss();
                if(response.isSuccessful())
                {

                    if(response.body().getResultCode().equals("108")){
                        otp.setText("");
                        Bundle bundle = new Bundle();
                        MainActivity.login.setArguments(bundle);
                        MainActivity.changelayout(4);
                        global.ShowMessage(response.body().getResultMessage());

                    } else if(response.body().getResultCode().equals("102")){


                        global.SavePData("PersonalResult",response.body());
                        if(global.GetValue("Authentication").equals("YY")) {
                            global.SaveValue("Authentication", "Y");
                            global.SaveValue("Home", "y");
                            Calendar c=Calendar.getInstance();
                            global.putDate("Login", c.getTimeInMillis());
                        }



                        Bundle bundle = new Bundle();
//                        MainActivity.homeInfo.setArguments(bundle);
//                        ((HomeInfo)MainActivity.homeInfo).update();
//                        MainActivity.changelayout(2);

                       GetToken();
//                        HomeInfo nextFrag= new HomeInfo();
//                        Bundle bundle = new Bundle();
//                        nextFrag.setArguments(bundle);
//                        getActivity().getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.main_container, nextFrag, "findThisFragment")
//                                .addToBackStack(null)
//                                .commit();
//

//                        Otp nextFrag= new Otp();
//                        Bundle bundle = new Bundle();
//                        bundle.putString("Mobile", response.body().getMoreDetails());
//                        nextFrag.setArguments(bundle);
//                        getActivity().getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.otp, nextFrag, "findThisFragment")
//                                .addToBackStack(null)
//                                .commit();

                        // ShowMessage("تم تسجيل الدخول");
                    }else {
                        global.ShowMessage(response.body().getResultMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<PersonalResult> call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }

        });



    }



    private void GetToken() {
//ToDo
        Intent intent = new Intent(getActivity(), MainLGActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

//        String user=global.GetValue("Username").replace("u","").replace("U","");
//        //String user="290550";
//        //String user="290404";
//
//        Call<GetToken> call = RetrofitClient.getInstance(Api.TaskURL).getMyApi().GetToken(user,"NDlEOTBGQzI4NDc5NDE3MjgwNTUyNEVBNkYwRUE0MzE=",global.GetValue("NotfToken"));
//        PorgressDilog dialog =  new PorgressDilog(getActivity());
//        dialog.show();
//        call.enqueue(new Callback<GetToken>() {
//            @Override
//            public void onResponse(Call<GetToken> call, Response<GetToken> response) {
//                Log.d("Resp",response.message()+"");
//                if(response.isSuccessful())
//                {
//                    dialog.dismiss();
//                    global.SaveValue("TaskToken","Bearer "+response.body().getData());
//                    global.SaveValue("IsSuper",response.body().getIsSupervisorUser());
//                    global.SaveValue("MID",user);
//
//                    Log.d("Reeeeeeeeeee","TaskToken");
//                    if(response.body().getIsSupervisorUser().contains("rue")) {
//                        GetTaskCount();
//                    }else{
//
//                        Intent intent = new Intent(getActivity(), MainLGActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//
//                    }
////                    if(response.body()==1){
////                        dialog.dismiss();
////                        global.ShowMessageNF(response.body().getMessage(),TaskAddTimeActivity.this);
////
////
////                    }else {
////                        dialog.dismiss();
////                        global.ShowMessage("");
////                    }
//
//                }else {
//
//                    dialog.dismiss();
//                    GetToken();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GetToken>call, Throwable t) {
//                dialog.dismiss();
//                GetToken();
//
//                Log.d("Reeeeeeeeeee",t.getMessage()+"");
//
//            }
//
//
//        });
    }

    private void GetTaskCount() {
//ToDo
        String user=global.GetValue("Username").replace("u","").replace("U","");
        //String user="290550";
        //String user="290404";

        Call<MangerCount> call = RetrofitClient.getInstance(Api.TaskURL).getMyApi().GetManagerTasksCount(global.GetValue("TaskToken"));
        PorgressDilog dialog =  new PorgressDilog(getActivity());
        dialog.show();
        call.enqueue(new Callback<MangerCount>() {
            @Override
            public void onResponse(Call<MangerCount> call, Response<MangerCount> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {
                    dialog.dismiss();
                    global.SaveValue("EmpTaskCount",response.body().getModel().getActiveTasksCount()+"");
                    global.SaveValue("ApprovalWaiting",(response.body().getModel().getIncreaseTaskTimeRequestsCount()+response.body().getModel().getApproveRequestTaskCount()+response.body().getModel().getReturnedTasksCount())+"");


                    Intent intent = new Intent(getActivity(), MainLGActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);


                    Log.d("Reeeeeeeeeee","TaskToken");


//                    if(response.body()==1){
//                        dialog.dismiss();
//                        global.ShowMessageNF(response.body().getMessage(),TaskAddTimeActivity.this);
//
//
//                    }else {
//                        dialog.dismiss();
//                        global.ShowMessage("");
//                    }

                }else {

                    dialog.dismiss();
                    GetTaskCount();
                }
            }

            @Override
            public void onFailure(Call<MangerCount>call, Throwable t) {
                dialog.dismiss();
                GetTaskCount();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }

}

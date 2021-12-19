package com.gov.sa.swcc;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gov.sa.swcc.model.LoginResult;
import com.gov.sa.swcc.model.PersonalResult;

import java.nio.charset.StandardCharsets;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


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
        Mobmsg.setText("تم إرسال الرمز للجوال ******"+Mobile.substring(Mobile.length()-4));
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
                CallLogin();

            }
        });

        sub_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CallLoginOTP(otp.getText().toString());
            }
        });











        return view;
    }


    private void CallLogin() {
        String user=global.GetValue("Username");
        String pass=global.GetValue("Password");

        byte[] data = pass.getBytes(StandardCharsets.UTF_8);
        String base64 = Base64.encodeToString(data, Base64.DEFAULT);

        Log.d("base64",base64+"");

        Call<LoginResult> call = RetrofitClient.getInstance().getMyApi().Login(user,base64.trim(),"null");
        ProgressDialog dialog = ProgressDialog.show(getActivity(), "",
                "يرجى الإنتظار", true);
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Response<LoginResult> response, Retrofit retrofit) {
                Log.d("Resp",response.message()+"");
                dialog.dismiss();
                if(response.isSuccess())
                {
                    if(response.body().getResultMessage().equals("Success: Message has been sent")){


                        Timer.start();
                        resend.setVisibility(View.GONE);
                        resend_pro.setVisibility(View.VISIBLE);
                    }else {
                        global.ShowMessage("الرقم الوظيفي و كلمة المرور غير صحيحة");

                    }

                }
            }

            @Override
            public void onFailure(Throwable t) {
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

        Call<PersonalResult> call = RetrofitClient.getInstance().getMyApi().LoginOTP(user,base64.trim(),Otp);
        ProgressDialog dialog = ProgressDialog.show(getActivity(), "",
                "يرجى الإنتظار", true);
        call.enqueue(new Callback<PersonalResult>() {
            @Override
            public void onResponse(Response<PersonalResult> response, Retrofit retrofit) {
                Log.d("Resp",response.message()+"");
                dialog.dismiss();
                if(response.isSuccess())
                {

                    Log.d("Resp",response.body().toString()+"");

                    if(response.body().getResultMessage().equals("done")){


                        global.SavePData("PersonalResult",response.body());


                        HomeInfo nextFrag= new HomeInfo();
                        Bundle bundle = new Bundle();
                        nextFrag.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_container, nextFrag, "findThisFragment")
                                .addToBackStack(null)
                                .commit();


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
                        global.ShowMessage("عفوا رمز التحقق غير صحيح");

                    }

                }
            }

            @Override
            public void onFailure(Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }
}

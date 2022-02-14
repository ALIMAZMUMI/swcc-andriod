package com.gov.sa.swcc;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.biometric.BiometricManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.CancellationSignal;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

import com.google.gson.Gson;
import com.gov.sa.swcc.model.LoginResult;
import com.gov.sa.swcc.model.PersonalResult;

import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {


    private CancellationSignal cancellationSignal = null;

    // create an authenticationCallback
    private BiometricPrompt.AuthenticationCallback authenticationCallback;


    Global global;
    public Login() {
        // Required empty public constructor
    }

EditText user,pass;
    CheckBox switch1;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

global=new Global(getContext());
        View view= inflater.inflate(R.layout.fragment_login, container, false);

        user=(EditText)view.findViewById(R.id.user);
        pass=(EditText)view.findViewById(R.id.password);
        switch1=(CheckBox) view.findViewById(R.id.switch1);



        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Button login=(Button)view.findViewById(R.id.button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String PerInfo=" {\"ResultCode\":null,\"ResultMessage\":\"success\",\"MoreDetails\":\"true\",\"ResultObject\":{\"FirstNameAr\":\"عبدالله\",\"MiddleNameAr\":\"محمد\",\"LastNameAr\":\"صعيدي\",\"FirstNameEn\":\"Abdullah\",\"MiddleNameEn\":\"Abdulaziz\",\"LastNameEn\":\"Saeidy\",\"FullName\":\"عبدالله محمد عبدالعزيز صعيدي\",\"Gender\":\"M\",\"NationalId\":\"1040122714\",\"Nationality\":\"السعودية\",\"Mobile\":\"966505555987\",\"Title\":\"رجل امن\",\"Department\":\"منظومة إنتاج الشعيبة\",\"DepartmentCode\":\"1010000\",\"LocationAr\":\"الرياض\",\"LocationEn\":\"Riyadh\",\"Photo\":\"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\"}}";
//                global.SaveValue("Username","u"+"208461");
//
//                Gson gson = new Gson();
//
//                PersonalResult per=gson.fromJson(PerInfo,PersonalResult.class);
//
//                global.SavePData("PersonalResult",per);
//
//
//                HomeInfo nextFrag= new HomeInfo();
//                Bundle bundle = new Bundle();
//                nextFrag.setArguments(bundle);
//                getActivity().getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.main_container, nextFrag, "findThisFragment")
//                        .addToBackStack(null)
//                        .commit();


                if (user.getText().toString().length() > 0 && pass.getText().toString().length() > 0) {
                    if(global.CheckInternet()) {
                    }else{
                    CallLogin();
                    }
                }

//                else if(global.GetPData("PersonalResult")!=null) {
//
//startActivity(new Intent(getActivity(),MainLGActivity.class));
//                    //MainActivity.changelayout(2);
//                }

                //global.SaveValue("Username","u"+"208461");
//
////    HomeInfo nextFrag= new HomeInfo();
////    Bundle bundle = new Bundle();
////
////    nextFrag.setArguments(bundle);
////
////
////    getActivity().getSupportFragmentManager().beginTransaction().show(nextFrag).commit();
//
//                   // MainActivity.changelayout(2);
////            .replace(R.id.main_container, nextFrag, "findThisFragment")
////            .addToBackStack(null)
////            .commit();
//}
            }
        });





        return view;
    }


    private void CallLogin() {



//        global.SaveValue("Username","u190250");
//        global.SaveValue("Password","SA.RUH.1409");
//        Otp nextFrag= new Otp();
//
//        Bundle bundle = new Bundle();
//        bundle.putString("Mobile", "0568935924");
//        nextFrag.setArguments(bundle);
//        getActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.main_container, nextFrag, "findThisFragment")
//                .addToBackStack(null)
//                .commit();


        byte[] data = pass.getText().toString().getBytes(StandardCharsets.UTF_8);
        String base64 = Base64.encodeToString(data, Base64.DEFAULT);

        Log.d("base64",base64+"");

        Call<LoginResult> call = RetrofitClient.getInstance().getMyApi().Login("u"+user.getText().toString().trim(),base64.trim(),"null");
        ProgressDialog dialog = ProgressDialog.show(getActivity(), "",
                "يرجى الإنتظار", true);
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                Log.d("Resp",response.message()+"");
                dialog.dismiss();
                if(response.isSuccessful())
                {
                    if(response.body().getResultMessage().equals("Success: Message has been sent")){

                        global.SaveValue("Username","u"+user.getText().toString().trim());
                        global.SaveValue("Password",pass.getText().toString());

                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P){
                            if(switch1.isChecked()) {
                                global.SaveValue("Authentication", "YY");
                            }
                        } else{

                        }


                        Bundle bundle = new Bundle();
                        bundle.putString("Mobile", response.body().getMoreDetails());
                        MainActivity.otp.setArguments(bundle);
                        MainActivity.changelayout(1);
//                        nextFrag.setArguments(bundle);
//                        getActivity().getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.main_container, nextFrag, "findThisFragment")
//                                .addToBackStack(null)
//                                .commit();

                        // ShowMessage("تم تسجيل الدخول");
                    }else {
                        ShowMessage("الرقم الوظيفي و كلمة المرور غير صحيحة");

                    }}


                }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }

        });
    }


public void ShowMessage(String Message){



    AlertDialog alertDialog1 = new AlertDialog.Builder(
            getActivity()).create();

    // Setting Dialog Title
    alertDialog1.setTitle("");

    // Setting Dialog Message
    alertDialog1.setMessage(Message);

    alertDialog1.setButton("موافق", new DialogInterface.OnClickListener() {

        public void onClick(DialogInterface dialog, int which) {

        }
    });

    // Showing Alert Message
    alertDialog1.show();

}
//    @Override
//    public void onResponse(Response<LoginResult> response, Retrofit retrofit) {
//        Log.d("Resp",response.isSuccess()+"");
//
//        if(response.isSuccess())
//        {
//            Log.d("URLCall",response.body().toString());
//
//        }
//
//
//    }
//
//    @Override
//    public void onFailure(Throwable t) {
//
//    }
}
